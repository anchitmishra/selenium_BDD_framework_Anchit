Feature: Database testing

    @fetchData
    Scenario Outline: To fetch data from table test1
        Given user fetches data from "<tableName>"
        Examples:
            | tableName |
            | test1     |

    @createData
    Scenario Outline: To crate new entry in table test1
        Given user adds new row into table "<tableName>" with values
            | id      | empName | grade |
            | 2078799 | Pankaj  | C3    |
        Given user fetches data from "<tableName>"
        Examples:
            | tableName |
            | test1     |