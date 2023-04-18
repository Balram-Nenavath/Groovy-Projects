package groovy.example.application.controller

import static org.junit.jupiter.api.Assertions.*
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.*
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.context.WebApplicationContext

import com.fasterxml.jackson.databind.ObjectMapper

import groovy.example.application.entity.Airport
import groovy.example.application.entity.Task
import groovy.example.application.exception.NoSuchTaskExistsException
import groovy.example.application.exception.TaskAlreadyExistsException
import groovy.example.application.repository.TaskRepository
import groovy.example.application.service.TaskService

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WithMockUser(username = "balram", password = "balram@1998*", roles = { "USER", "ADMIN" })
class TaskControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext

	@MockBean
	AuthenticationManager authenticationManager
	
	@MockBean
	TaskService service
	
	@MockBean
	TaskRepository repo
	
	private static MockMvc mockMvc
	
    @BeforeEach
    void setUp() {
    	if(mockMvc == null)
		{
			synchronized(TaskControllerTest.class) {
				if(mockMvc == null)
				{
					mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
				}
			}
		}
    }
	

    @Test
    void home() {
		mockMvc.perform(get("/tasks/login-check"))
		.andExpect(status().isOk())
		  }

    @Test
    void findAll() {
		mockMvc.perform(get("/tasks/findAll"))
		.andExpect(status().isOk())
    }

    @Test
    void findById() throws Exception {
    	mockMvc.perform(get("/tasks/find/1")).andExpect(status().isOk())//.andExpect(jsonPath('$.title').value('RRR'))
    }

	
 
	
	//mock1
	@Test
	void saveTaskSuccess() throws Exception {
		Task t = new Task()
    	t.setTitle("NON")
    	t.setDescription("IMovie")
		ObjectMapper mapper=new	ObjectMapper()
		when(service.saveTask(any())).thenReturn(t.toString())
		mockMvc.perform(post("/tasks/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(t)))
		.andDo(print()).andExpect(status().isOk())
	}
	
	//mock2
	@Test
	void saveTaskFailed() throws Exception
	{
		
		Task t = new Task()
    	t.setTitle("NON")
    	t.setDescription("IMovie")
		ObjectMapper mapper=new	ObjectMapper()
		when(service.saveTask(any())).thenThrow(TaskAlreadyExistsException.class)
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.put("/tasks/update")
		.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
		.content(mapper.writeValueAsString(t))
this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().is5xxServerError())
		//assertEquals(TaskAlreadyExistsException.class,service.saveTask(any()))
	}


	@Test
	void updateTaskSuccess() throws Exception
	{
		
		Task t = new Task(24,"Radhe","Indian-Movie")
		ObjectMapper mapper	=new ObjectMapper()
		when(service.updateTask(any())).thenReturn(t.toString())
		MockHttpServletRequestBuilder builder=MockMvcRequestBuilders.put("/tasks/update")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(t))
		this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
	}
	

	//mock2
	@Test
	void updateTaskFailed() throws Exception
	{
		Task t = new Task(20,"Radhe","Indian-Movie")
		ObjectMapper mapper	=new ObjectMapper()
		String str= mapper.writeValueAsString(t)
		when(service.updateTask(t)).thenThrow(NoSuchTaskExistsException.class)
		mockMvc.perform(put("/tasks/update").contentType("application/json").content(str))
		.andDo(print()).andExpect(status().isInternalServerError())
	}
	
	

    @Test
    void deleteById() {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/delete/{id}", 23)
				).andDo(print()).
				andExpect(status().isOk())
    }
	
	
    @Test
    void patchUpdateTask() {
		
		
		String plainCreds = "user:balram@1998";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		
		String url="http://localhost:9090/tasks/patchUpdate/21"
		RestTemplate restTemplate = new RestTemplate()
		HttpClient httpClient = HttpClientBuilder.create().build()
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
		HttpHeaders reqHeaders = new HttpHeaders()
		
		reqHeaders.add("Authorization", "Basic " + base64Creds);
		reqHeaders.setContentType(MediaType.APPLICATION_JSON)
		HttpEntity<String> requestEntity = new HttpEntity<String>("{\"title\": \"SITA\"}", reqHeaders)
		ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.PATCH,
				requestEntity, String.class)
    }

    @Test
    void saveAllTask() {
		List<Task> temp = new ArrayList<>()
		Task t1 = new Task()
		t1.setTitle("NNN")
		t1.setDescription("Movie")
		temp.add(t1)
		Task t2 = new Task()
		t2.setTitle("MMM")
		t2.setDescription("Movie")
		temp.add(t2)
		ObjectMapper objMap = new ObjectMapper()
		String str= objMap.writeValueAsString(temp)
		MvcResult mvcResult = mockMvc.perform(post("/tasks/addAll").contentType(MediaType.APPLICATION_JSON).content(str)).andReturn()
		assertEquals(200,mvcResult.getResponse().getStatus())
    }

    @Test
    void updateAllTask() {
		List<Task> temp = new ArrayList<>()
		Task t1 = new Task(36,"NNN","Indian-Movie")
		Task t2 = new Task(37,"MUN","Movie")
		temp.add(t1)
		temp.add(t2)
		ObjectMapper objMap = new ObjectMapper()
		String str= objMap.writeValueAsString(temp)
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/tasks/updateAll").contentType(MediaType.APPLICATION_JSON).content(str)).andReturn()
		assertEquals(200,mvcResult.getResponse().getStatus())
    }

    @Test
    void deleteAllTasks() throws Exception{
	
		int id1 = 36
		int id2 = 37
		String url = "/tasks/deleteAll?id="+id1+","+id2
			mockMvc.perform(MockMvcRequestBuilders.delete(url)
			).andDo(print()).
			andExpect(status().isOk())
		
    }
	
	
	@Test
	void postAirportDetails() {
		Airport	temp=new Airport(3081,"HYD","VOHS","Rajiv Gandhi International Airport","Hyderabad, Telangana, India","","","Hyderabad","Ranga Reddy","Telangana","IN","India","500409","+91 40 6654 6370",17.240263,78.42938,330,"http://www.hyderabad.aero/")
		
		ObjectMapper objMap = new ObjectMapper()
				String str= objMap.writeValueAsString(temp)
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/tasks/postAirport").contentType(MediaType.APPLICATION_JSON).content(str)).andReturn()
				assertEquals(200,mvcResult.getResponse().getStatus())
	}
	
	@Test
	void getAirportDetails() {
		
	Airport	resp=new Airport(1734,"DEL","VIDP","Indira Gandhi International Airport","Delhi, India","","","New Delhi","","Delhi","IN","India","110037","+91 124 337 6000",28.556162,77.09996,330,"http://www.newdelhiairport.in/")

	LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>()
		requestParams.add("iata", "DEL")
		requestParams.add("icao", "VIDP")
		when(service.getDetailsofAirport(any(),any())).thenReturn(String.valueOf(resp))
		mockMvc.perform(get("/tasks/airportTest").params(requestParams)).andExpect(status().isOk())
	}
	
	@Test
	void getAirportDetailsFailed()
	{
		String testAirport1="DEL"
		String testAirport2="VIDP"
		Airport	resp=new Airport(1734,"DEL","VIDP","Indira Gandhi International Airport","Delhi, India","","","New Delhi","","Delhi","IN","India","110037","+91 124 337 6000",28.556162,77.09996,330,"http://www.newdelhiairport.in/")
		when(service.getDetailsofAirport(testAirport1, testAirport2)).thenReturn(null)
		mockMvc.perform(get("/tasks/airportTest").contentType("application/json").content(testAirport1)).andDo(print())
		.andExpect(status().isBadRequest())
	}
    
  
}