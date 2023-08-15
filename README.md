# Tea_OAuth2_AuthServer
Сервер для авторизации пользователей и клиентов, предоставляющий токен для работы с приложением.
Является частью общего pet-проекта по освоению технологий OAuth2 и REST 
(ссылки на [клиентскую часть](https://github.com/Cracaziabra/Tea_OAuth2_Client) и [сервер ресурсов](https://github.com/Cracaziabra/Tea_OAuth2_ResourceServer)).

## Описание
Отдельный сервер для авторизации пользователей в системе. Позволяет отделить процесс авторизации от основного приложения, тем самым сохраняя данные пользователя от кражи.
После авторизации предоставляет клиенской части токен, с помощью которого можно получить доступ к серверу ресурсов.
Токен создается по стандарту JWT и не содержит авторизационных данных пользователя (только права доступа).

К приложению написаны тесты для сервисов. С помощью GitHub Actions организована проверка тестов при push и pull-request операциях.

## Изученные технологии
Spring (Boot, Security, Data JPA, Test)\
OAuth2\
PostgreSQL\
Maven\
Git\
GitHub (+CI)
