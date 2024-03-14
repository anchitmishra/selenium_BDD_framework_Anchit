@guru99Login

Feature: open guru99 demo banking page enter login credentials and login
    Background: launching guru99
        Given user is on guru99 demo banking page "https://www.demo.guru99.com/V4/index.php"

    @validLogin
    Scenario Outline: login to guru99 banking page with valid credentials
        Then the user enters the username "<username>"
        And the user enters the password "<password>"
        And the user clicks on login button
        And the user should be landing on the homepage of the banking application and see manager ID as "<username>"
        Examples:
            | username   | password |
            | mngr549323 | dEgAjAt  |

    @invalidLogin
    Scenario Outline: enter invalid credentials
        Then the user enters the username "<username>"
        And the user enters the password "<password>"
        And the user clicks on login button
        And the user is navigated back to login page
        Examples:
            | username   | password |
            | mngr549323 | 1234     |
            | abcd       | dEgAjAt  |
            | abcd       | 1234     |

    @reset
    Scenario Outline: checking functionality of reset button
        Then the user enters the username "<username>"
        And the user clicks the reset button
        Then the credentials field should be reset
        Examples:
            | username   |
            | mngr549323 |

    @newAccount
    Scenario Outline: add new account
        Then the user enters the username "<username>"
        And the user enters the password "<password>"
        And the user clicks on login button
        And the user should be landing on the homepage of the banking application and see manager ID as "<username>"
        And the user clicks on new account
        And the user enters new account details with customer id as "<custID>" account type as "<accType>" and initial deposit as "<deposit>"
        And the user clicks on submit
        Then the user should be able to see new account details
        Examples:
            | username   | password | deposit | custID | accType |
            | mngr549323 | dEgAjAt  | 10000   | 8282   | Savings |

    @newCustomer
    Scenario Outline: add new customer
        Then the user enters the username "<username>"
        And the user enters the password "<password>"
        And the user clicks on login button
        And the user should be landing on the homepage of the banking application and see manager ID as "<username>"
        And the user clicks on new customer tab
        And the user enters basic customer details
            | customerName   | gender | dateOfBirth | address       | city   | state  | PIN    | mobileNumber | eMail         | password |
            | <customerName> | m      | 10-10-1990  | bundel street | bundel | snathi | 575757 | 2298434037   | ray@cobol.com | con@123  |
        And the user clicks on submit
        Then the user validates "<customerName>" registered successfully message on the screen
        Examples:
            | username   | password | customerName |
            | mngr549323 | dEgAjAt  | ray mishra   |

    @customizedStatement
    Scenario Outline: generate customized statement
        Then the user enters the username "<username>"
        And the user enters the password "<password>"
        And the user clicks on login button
        And the user should be landing on the homepage of the banking application and see manager ID as "<username>"
        And the user clicks on customised statement
        And the user enters the account details
            | accountNumber   | fromDate   | toDate   | minTxnValue | noOfTxn |
            | <accountNumber> | ---------- | -------- | -------     | ------  |
        And the user clicks on submit
        Examples:
            | username   | password | accountNumber |
            | mngr549323 | dEgAjAt  | 131914        |

    @testDryRun
    Scenario: Test the dry run feature
        Then the user tries to test dry run
        And the user is successful 

