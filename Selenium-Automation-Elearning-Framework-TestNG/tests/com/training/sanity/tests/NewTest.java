package com.training.sanity.tests;

import org.testng.annotations.Test;

public class NewTest {
	
	@Test
	public void M() {
		System.out.println("E");
	}

	@Test (priority = 1)
	public void A() {
		System.out.println("A");
	}

	@Test (priority = 2)
	public void B() {
		System.out.println("B");
	}

	@Test (dependsOnMethods = "M")
	public void BA() {
		System.out.println("C");
	}

	@Test
	public void AB() {
		System.out.println("D");
	}

	/*
	 * @Test public void B() { System.out.println("second"); }
	 * 
	 * @Test public void E() { System.out.println("fifth"); }
	 * 
	 * @Test (priority=1) public void C() { System.out.println("third"); }
	 * 
	 * @Test (priority=2) public void D() { System.out.println("fourth"); }
	 * 
	 * @Test public void A() { System.out.println("first"); }
	 */
}
