# 🏦 API de Transacciones Bancarias

API REST profesional para la gestión y transferencia de dinero entre cuentas bancarias, desarrollada bajo los principios de **Arquitectura Hexagonal (Clean Architecture)** y **Domain-Driven Design (DDD)**.

---

## 🛠️ Tecnologías Utilizadas
* **Java 17 / 21** - Lenguaje principal.
* **Spring Boot 3.x** - Framework de desarrollo.
* **Spring Data JPA** - Persistencia de datos.
* **H2 Database** - Base de datos en memoria para pruebas rápidas.
* **Postman** - Documentación y pruebas de endpoints.

---

## 📐 Arquitectura del Proyecto (Hexagonal)
El proyecto está estructurado de manera que las reglas de negocio estén completamente aisladas de la infraestructura externa:

* **Domain (Núcleo):** Contiene los modelos lógicos (`Account`, `Money`, `Transaction`) libres de frameworks.
* **Application (Casos de Uso):** Define los puertos e implementa los servicios como `TransferMoneyService`.
* **Infrastructure (Adaptadores):** Maneja los controladores REST (`AccountController`) y los repositorios JPA de la base de datos.

---

## 📖 Documentación de la API (Endpoints)

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

## 🚀 Cómo Probar el Servicio (Postman)
Para facilitarle la revisión a los reclutadores, he incluido una colección de pruebas lista para usar:

1. Descarga el archivo de colección ubicado en la carpeta `postman/banking-api.json` de este repositorio.
2. Abre **Postman** e impórtalo.
3. Inicia la aplicación en tu IDE (Spring Boot levantará la base de datos H2 automáticamente).
4. Ejecuta las peticiones en orden para validar el comportamiento del sistema.
