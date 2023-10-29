# Exercise

Implement a daily lunch ordering application.

## Specification
Your application should expose three HTTP endpoints:


### API Definition: 

```
/daily/menu
```

### API Input:

This API has no specific input requirements.

### API Response:

For each request to this API endpoint, return a list of available meals.

### Workflow:

When the API is called, your code should crawl table data from the following source:
<table> <tbody>
  <tr>       					
      <td>mealId</td>
      <td>mealName</td>
      <td>price</td>
  </tr>
  <tr>       					
      <td class="mealId">1</td>
      <td class="mealName">Varivo od mix mahunarki</td>
      <td class="price">3,60€</td>
  </tr>
  <tr>       					
      <td class="mealId">2</td>
      <td class="mealName">Pohani file oslića – krumpir salata s rikulom</td>
      <td class="price">6,30€</td>
  </tr> 
  <tr>       					
      <td class="mealId">3</td>
      <td class="mealName">Pohani file oslića, umak od vlasca i krastavca - slani krumpir</td>
      <td class="price">6,30€</td>
  </tr>
  <tr>       					
      <td class="mealId">4</td>
      <td class="mealName">Steak tune sa žara, tršćanski umak – blitva s krumpirom</td>
      <td class="price">10€</td>
  </tr>
  <tr>       					
      <td class="mealId">5</td>
      <td class="mealName">Orada sa žara, tršćanski umak – blitva s krumpirom</td>
      <td class="price">7,10€</td>
  </tr>
  <tr>       					
      <td class="mealId">6</td>
      <td class="mealName">Crni rižoto od liganja s parmezanom</td>
      <td class="price">6,50€</td>
  </tr>
  <tr>       					
      <td class="mealId">7</td>
      <td class="mealName">Pureći medaljoni u umaku od pesta s tjesteninom</td>
      <td class="price">6€</td>
  </tr>
  <tr>       					
      <td class="mealId">8</td>
      <td class="mealName">Juha od rajčice</td>
      <td class="price">1,20€</td>
  </tr>
  <tr>       					
      <td class="mealId">9</td>
      <td class="mealName">Salata miješana</td>
      <td class="price">1,10€</td>
  </tr>
</tbody></table>

Your code should then return a list of meals as the response, with the **_mealId_** serving as a unique identifier for each meal.


### API Definition: 

```
/order
```

### API Input:

This API endpoint takes list of meal IDs that user wants to order.

### API Output:

Returns the ID of the stored order, which will be used as input for the third API endpoint.

### Workflow:

When this API is called, create a new order and return its identifier.


### API Definition: 

```
/order/{id}
```

### API Input:

This API endpoint takes order ID as input:

https://lunch/order/1

### API Output:

Returns the order with updated values.

### Workflow:

When this API is called, update the existing order with newly requested meal IDs.


### Additional Functionality
Each day at 10:30, send an email to a configurable email address with a summarized list of meal IDs.

**eMail content example**
```
Hello, we would like to order the following:

2 x Pohani file oslića, umak od vlasca i krastavca - slani krumpir
1 x Orada sa žara, tršćanski umak – blitva s krumpirom
3 x Juha od rajčice

Regards,
Leapwise team
```

## Additional Information
You should use the following frameworks for your work.

Spring JPA
H2 database running in memory (data will not be persistent across application restarts)
You are free to add / change any libraries that you might need to solve this exercise, the only requirement is that we do not have to setup / install any external software to run this application.

Running the exercise with maven

```mvn spring-boot:run```

### Commiting
You will provide your solution by creating a feature branch using your name (i.e. feature/ivanhorvat) and pushing it to this repository.
