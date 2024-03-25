Feature: Test para consulta de cliente
  Background:
    * url baseUrl

  Scenario: Obtener no existencia del cliente
    Given path 'clientes/43163052'
    And header Accept = 'application/json'
    When method GET
    Then status 409

  Scenario: Registro satisfactorio de cliente
    * def jsonBody =
      """
      {
        "nombre": "Maria Santos",
        "genero": "M",
        "edad": 25,
        "identificacion": "43163058",
        "direccion": "Av. Mariscal Miller 1930",
        "telefono": "991279833",
        "contrasena": "1234"
      }
      """
    Given path 'clientes'
    And header Accept = 'application/json'
    And request jsonBody
    When method POST
    Then status 201
    And match response.identificacion == "43163058"
    And match response.estado == 1

  Scenario: Eliminacion satisfactorio de cliente
    Given path 'clientes/43163058'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

