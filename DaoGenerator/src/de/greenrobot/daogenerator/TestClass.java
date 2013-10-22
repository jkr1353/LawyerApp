package de.greenrobot.daogenerator;


public class TestClass {

	Schema schema;
	
	public static void main(String[] args) throws Exception {
        TestClass testDaoGenerator = new TestClass();
        testDaoGenerator.generate();
    }
	
	private void generate() throws Exception {
		DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema, "/Users/Austin/Documents/workspace/TestingDao/src");
	}

	public TestClass()
	{
		
		schema = new Schema(1, "com.example.testingdao");
		/*
		Entity contact = schema.addEntity("Contact");
		contact.addLongProperty("caseID");
		contact.addStringProperty("name");
		contact.addStringProperty("phoneNumber");
		contact.addStringProperty("address");
		contact.addStringProperty("notes");
		contact.addStringProperty("picLocation");
		*/
		Entity log = schema.addEntity("Logs");
		log.addIdProperty();
		//log.addLongProperty("caseID");
		log.addDateProperty("date");
		log.addStringProperty("notes");
		log.addFloatProperty("expenses");
		log.addFloatProperty("mileage");
		log.addFloatProperty("hours");
		/*
		Entity lawcase = schema.addEntity("Case");
		lawcase.addIdProperty();
		lawcase.addStringProperty("name");
		lawcase.addStringProperty("caseType");
		*/
	}
}