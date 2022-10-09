package TestUsuario;

import org.testng.annotations.Test;




import PageObjectoModel.Clientepage;
import PageObjectoModel.UsuarioPage;
import TestCliente.Test1;
import TestLogin.TestLogueo;
import TestMenu.Testm;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;



public class UsuarioTest1 {
	
	Clientepage ingresopage;
	UsuarioPage pruebac;
	String nombre="Maradonio";
	String apellido="enricoa";
	String correo="me@derfswr34";
	String pass="abc234$A";
	String passr="abc234$A";
	String opcionMenu="Usuarios";
	WebDriver driver;
	Testm tm =new Testm();
	

	
	@BeforeClass
	@Parameters({"URL","BrowserType","Usr","pass"})
	public void beforeClass(String url,String browser,String Usr,String pass) throws InterruptedException  {
		TestLogueo t1 =new TestLogueo();
		driver=t1.beforeClass(url, browser);
		t1.pruebaLogin(Usr, pass);
		tm.testMenu(driver,opcionMenu);
		ingresopage= new Clientepage (driver);
		pruebac= new UsuarioPage (driver);
		
	}
	
	
	@Test(description="test de logueo e ingreso de Usuario",priority=1)
  public void testIngresaUser() throws InterruptedException {
		
		//Assert.assertTrue(pruebac.IngresoUsuario(), "No se pudo loguear correctamente");
		pruebac.AgregaUsuario();	
		String mensajes[]=pruebac.ValidacampoUser ();
		for(int i=0;i<=4;i++){
			Assert.assertEquals(mensajes[i],"El campo es obligatorio" ,"los campos Nombre,Direccion ,correo y pass son obligatorios");	
		}
	
		Assert.assertEquals(pruebac.IngresarUsuario(nombre,apellido,correo, pass, passr), "El registro se guardo correctamente");
		Assert.assertTrue(ingresopage.BuscarCliente (nombre,1)!= 0, "No se encontro registro en la tabla");
	
  }
  

  @AfterMethod
  public void afterTest(ITestResult result) {
	  String status="";
	  if(result.getStatus()==1){
		  status="Exitosamente";
	  }
	  else{
		  
		  status="con inconvenientes";
	  }
	  
	 
	System.out.println("El "+result.getMethod().getDescription()+" fue ejecutado "+status);
	System.out.println("Test Case Finalizado..");

  }
  
	@AfterClass
	public void CloseBrowser() {
		
		driver.close();
	}

}
