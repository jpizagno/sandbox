package de.coding.quiz.OrderedKeyValues;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
	@Test
	public void values() {
		App app = new App();
		
		app.setValue("c",3);
		app.setValue("b",2);
		app.setValue("a",1);
		app.setValue("e", 7);
		app.setValue("d", 6); 
		app.setValue("f", 9);
		
		assertThat(app.values(), is("abcdef"));
	}
	
	@Test
	public void valuesOrderB() {
		App app = new App();
		
		app.setValue("c",3);
		app.setValue("a",1);
		app.setValue("b",2);
		app.setValue("e", 7);
		app.setValue("d", 6); 
		app.setValue("f", 9);
		
		assertThat(app.values(), is("abcdef"));
	}
	
	@Test
	public void valuesOrderC() {
		App app = new App();
		
		app.setValue("c",3);
		app.setValue("b",2);
		app.setValue("e", 70); // 70
		app.setValue("d", 6); 
		app.setValue("f", 100);  // 100
		app.setValue("a",1);
		
		assertThat(app.values(), is("abcdef"));
	}
	
	@Test
	public void valuesOrderD() {
		App app = new App();
		
		app.setValue("f", 9);
		app.setValue("e", 7);
		app.setValue("d", 6); 
		app.setValue("c",3);
		app.setValue("b",2);
		app.setValue("a",1);
		
		assertThat(app.values(), is("abcdef"));
	}
	
	@Test
	public void sameKey() {
		App app = new App();
		
		app.setValue("a",1);
		app.setValue("b",2);
		app.setValue("c",3);
		app.setValue("d", 6); 
		app.setValue("e", 7);
		app.setValue("f", 9);
		// insert same key, new value, "d" should go to the END because value=10
		app.setValue("d", 10);
		
		assertThat(app.values(), is("abcefd")); // "d" at End
	}
	
	@Test
	public void zeroDelete() {
		App app = new App();
		
		app.setValue("a",1);
		app.setValue("b",2);
		app.setValue("c",3);
		app.setValue("d", 6); 
		app.setValue("e", 7);
		app.setValue("f", 9);
		// insert same key, new value, "d" should be deleted because value=0
		app.setValue("d", 0);
		
		assertThat(app.values(), is("abcef")); // "d" removed
	}
}
