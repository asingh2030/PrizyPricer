# PrizyPricer
Store and product entities. with Spring boot, mvc, hibernate, mysql, test case, swagger and dynamic price calculation.
# Store Service
CRUD operation with add product.

# Product Service
CRUD operation with add price, get dynamic calculated ideal price.

# Technology stack-
Spring Boot, MVC, DAO, Security, JPA, Hibernate, Swagger, Junit, Dynamic bean creation, mysql
Will add in future Google location service to locate store.

# Test
we can test this app using Junit test cases and Swagger UI.

# Note
-Please use default username 'user' and password as generated when system starts to test from swagger.
swagger url - http://localhost:8080/swagger-ui.html
-Because of time limit I am not able to add many test cases.

# Dynamic ideal price service
To create new formula we need to create new class that implements IdealPriceService and provide calculate method implementation.
And to use your new formula service you need to create your bean name `Prefix`+IdealPriceService. And provide same prefix of your bean to prizy.ideal.price.service=`prefix`. 
For example:
@Service("defaultIdealPriceService")
public class DefaultIdealPriceServiceImpl implements IdealPriceService {
	@Override
	public Double calculate(Set<Double> priceSet) {return null;}
}
application.properties
your bean to prizy.ideal.price.service=`default`.  
Its like service locator pattern based on property value.