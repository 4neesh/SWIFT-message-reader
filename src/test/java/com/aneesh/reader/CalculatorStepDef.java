package com.aneesh.reader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorStepDef {


    Calculator calculator;
    Integer result = 0;

    @Given("I have a calculator")
    public void i_have_a_calculator(){

        this.calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2) {

        result = calculator.add(int1, int2);
    }



    @Then("I should have {int}")
    public void i_should_have(Integer int1) {

        assertEquals("Calculator cannot add two numbers", int1, result);
    }

}
