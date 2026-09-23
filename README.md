# Sprint_7 — Автотесты API Яндекс.Самокат

Проект по автоматизации тестирования REST API сервиса [Яндекс.Самокат](https://qa-scooter.praktikum-services.ru/docs/).

## Что проверяется

- Создание курьера (успех, дубликат, без логина, без пароля)
- Авторизация курьера (успех, неверный логин/пароль, без полей)
- Создание заказа (BLACK, GREY, оба, без цвета)
- Список заказов
- Удаление курьера
- Принятие заказа
- Получение заказа по трек-номеру

## Стек

- Java 11
- Maven
- JUnit 4
- RestAssured
- Allure
- Lombok

## Архитектура

- **Client** — HTTP-запросы (RestAssured)
- **Steps** — шаги с @Step (Allure)
- **Data** — генерация тестовых данных (UUID)
- **Model** — POJO (Lombok)
- **Test** — тестовые сценарии (JUnit 4)

## Структура проекта

```
src/
├── main/java/
│   ├── data/        — генерация данных
│   └── model/       — POJO
└── test/java/
    ├── client/      — HTTP-клиенты
    ├── steps/       — шаги
    └── *.java       — тесты
```

## Пример теста

```java
@Test
@DisplayName("Создание курьера: успешный сценарий")
@Description("Проверка, что курьера можно создать, код 201, ok: true")
public void createCourierSuccess() {
    Courier courier = CourierData.getRandomCourier();
    CourierSteps.createCourierAndCheck(courier);
}
```

## Запуск

1. Клонировать:

```bash
git clone git@github.com:PracticumLera/Sprint_7.git
```

2. Запустить тесты:

```bash
mvn clean test
```

3. Сгенерировать отчёт:

```bash
mvn allure:serve
```

## Автор

Valeria Belyayeva