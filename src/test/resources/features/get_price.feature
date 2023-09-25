Feature: Price Service API Testing

  @test1
  Scenario: Test 1 - Get price at 10:00 on day 14 for product 35455 and brand 1
    Given the current date is "2020-06-14-10.00.00"
    And the product ID is "35455"
    And the brand ID is "1"
    When the request for the price is done
    Then the response should include the following details:
      | Field      | Expected Value      |
      | Product ID | 35455               |
      | Brand ID   | 1                   |
      | Price List | 1                   |
      | Start Date | 2020-06-14-00.00.00 |
      | End Date   | 2020-12-31-23.59.59 |
      | Price      | 35.50               |

  @test2
  Scenario: Test 2 - Get price at 10:00 on day 14 for product 35455 and brand 1
    Given the current date is "2020-06-14-16.00.00"
    And the product ID is "35455"
    And the brand ID is "1"
    When the request for the price is done
    Then the response should include the following details:
      | Field      | Expected Value      |
      | Product ID | 35455               |
      | Brand ID   | 1                   |
      | Price List | 2                   |
      | Start Date | 2020-06-14-15.00.00 |
      | End Date   | 2020-06-14-18.30.00 |
      | Price      | 25.45               |

  @test3
  Scenario: Test 3 - Get price at 10:00 on day 14 for product 35455 and brand 1
    Given the current date is "2020-06-14-21.00.00"
    And the product ID is "35455"
    And the brand ID is "1"
    When the request for the price is done
    Then the response should include the following details:
      | Field      | Expected Value      |
      | Product ID | 35455               |
      | Brand ID   | 1                   |
      | Price List | 1                   |
      | Start Date | 2020-06-14-00.00.00 |
      | End Date   | 2020-12-31-23.59.59 |
      | Price      | 35.50               |

  @test4
  Scenario: Test 4 - Get price at 10:00 on day 14 for product 35455 and brand 1
    Given the current date is "2020-06-15-10.00.00"
    And the product ID is "35455"
    And the brand ID is "1"
    When the request for the price is done
    Then the response should include the following details:
      | Field      | Expected Value      |
      | Product ID | 35455               |
      | Brand ID   | 1                   |
      | Price List | 3                   |
      | Start Date | 2020-06-15-00.00.00 |
      | End Date   | 2020-06-15-11.00.00 |
      | Price      | 30.50               |

  @test5
  Scenario: Test 5 - Get price at 10:00 on day 14 for product 35455 and brand 1
    Given the current date is "2020-06-16-21.00.00"
    And the product ID is "35455"
    And the brand ID is "1"
    When the request for the price is done
    Then the response should include the following details:
      | Field      | Expected Value      |
      | Product ID | 35455               |
      | Brand ID   | 1                   |
      | Price List | 4                   |
      | Start Date | 2020-06-15-16.00.00 |
      | End Date   | 2020-12-31-23.59.59 |
      | Price      | 38.95               |