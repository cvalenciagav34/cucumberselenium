#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@tagdemo
Feature: Entrar en sesión
  Quiero poder entrar en sesión en el sistema

	@taglogin
  Scenario Outline: Entrar en sesion
    Given Quiero entrar en sesion con mi cuenta de usuario o administrador
    When Relleno el formulario con mi "<login>" y mi "<password>"
    Then Compruebo el titulo de la "<ventana>" a la que acudo

    Examples: 
      | login | password | ventana   |
      | user  | user     | userhome  |
      | user  | userko   | login     |
      | admin | admin    | adminhome |
      | admin | adminko  | login     |
      | user2 | user2    | userhome  |