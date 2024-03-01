package net.unir.missi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginStepDefinition extends BasicStepDefinition {

    @Given("Quiero entrar en sesion con mi cuenta de usuario o administrador")
    public void quiero_entrar_en_sesion_con_mi_cuenta_de_usuario_o_administrador() {
        createPDFFile();
        addTexto("Inicio de prueba: Quiero entrar en sesion con mi cuenta de usuario o administrador");
    }

    @When("Relleno el formulario con mi {string} y mi {string}")
    public void relleno_el_formulario_con_mi_y_mi(String username, String password) {
        addTexto("Relleno el formulario con mi username " + username + " y mi password " + password);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        long noOfSeconds = 10;
        dr = new ChromeDriver();
        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(noOfSeconds));
        dr.manage().window().maximize();
        dr.get("http://localhost:8080/login");
        dr.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        dr.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        capturarPantalla();
        dr.findElement(By.xpath("//*[@type='submit']")).click();
    }

    @Then("Compruebo el titulo de la {string} a la que acudo")
    public void compruebo_el_titulo_de_la_a_la_que_acudo(String ventana) throws InterruptedException {
        Thread.currentThread().sleep(2000);
        addTexto("Compruebo la ventana " + ventana + " a la que acudo");
        capturarPantalla();

        if (ventana.equals("login")) {
            String texto = dr.findElement(By.xpath("//*[@jhitranslate='login.title']")).getText();
            assertEquals(texto, "Iniciar la sesión");
            addTexto("Llego a la ventana con titulo " + texto);
        }

        if (ventana.equals("userhome")) {
            String texto = dr.findElement(By.xpath("//*[@id='home-logged-message']")).getText();
            assertEquals(texto, "Está conectado como \"user\".");
            addTexto("Llego a la ventana con titulo " + texto);
        }

        if (ventana.equals("adminhome")) {
            String texto = dr.findElement(By.xpath("//*[@id='home-logged-message']")).getText();
            assertEquals(texto, "Está conectado como \"admin\".");
            addTexto("Llego a la ventana con titulo " + texto);
        }

        dr.quit();
    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        if (scenario.isFailed()) {
            addTexto("Prueba finalizada con errores");
        } else {
            addTexto("Prueba finalizada OK!");
        }
        dr.quit();
        cerrarPdf();
    }
}
