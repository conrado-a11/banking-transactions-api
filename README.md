### 1. Transferir Dinero
Permite mover saldos entre dos cuentas existentes de forma segura.

* **Método:** `POST`
* **URL:** `/api/accounts/transfer`
* **Request Body (JSON):**
```json
{
  "sourceAccountId": "acc-1",
  "targetAccountId": "acc-2",
  "amount": 400.0
}
```
* **Validaciones:** El monto no puede ser negativo y la cuenta de origen debe contar con saldo suficiente.
* **Respuesta Exitosa:** `200 OK`

---

### 2. Consultar Cuenta por ID
Obtiene el estado actual y saldo de una cuenta específica.

* **Método:** `GET`
* **URL:** `/api/accounts/{id}`
* **Respuesta Exitosa:** `200 OK`
* **Response Body (JSON):**
```json
{
  "id": "acc-1",
  "customerId": "cliente-123",
  "balance": 500000.0
}
```

---

### 3. Realizar un Depósito
Permite ingresar dinero en efectivo a una cuenta.

* **Método:** `POST`
* **URL:** `/api/accounts/{id}/deposit`
* **Request Body (JSON):**
```json
{
  "amount": 50.0
}
```
* **Validaciones:** El monto debe ser mayor a cero.
* **Respuesta Exitosa:** `200 OK`

---

### 4. Realizar un Retiro
Permite retirar fondos de una cuenta si tiene saldo suficiente.

* **Método:** `POST`
* **URL:** `/api/accounts/{id}/withdraw`
* **Request Body (JSON):**
```json
{
  "amount": 30.0
}
```
* **Validaciones:** El monto no puede superar el saldo actual de la cuenta.
* **Respuesta Exitosa:** `200 OK`

---

## 🚀 Cómo Probar el Servicio (Postman)
Para facilitarle la revisión a los reclutadores, he incluido una colección de pruebas lista para usar:

1. Descarga el archivo de colección ubicado en la carpeta `postman/banking-api-collection.json` de este repositorio.
2. Abre **Postman** e impórtalo.
3. Inicia la aplicación en tu IDE (Spring Boot levantará la base de datos H2 automáticamente).
4. Ejecuta las peticiones en orden para validar el comportamiento del sistema.
