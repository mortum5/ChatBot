# ChatBot


#### Requires:
* JDK 11

-------------

### How to run application

#### 1. Clone repository
`git clone https://github.com/mortum5/ChatBot.git`

#### 2. Setup VK API properties
1. Open `src/main/resources/vk.properties`
2. Specify params gotten from VK

| Variable name       | Description                      |
 |---------------------|----------------------------------|
| vk.api.access_token | VK API access token              |
| vk.api.group_id     | VK API group id                  |
| vk.api.v            | VK API usage version             |


#### 3. Build and Run jar
On Linux host: `./mvnw clean package`

On Windows host: `mvnw.cmd clean package`


-------------

### Test case

Необходимо выполнить интеграцию с [BotAPI VK](https://vk.com/dev/bots_docs).

В рамках задания нужно создать бота который при его упоминании будет
цитировать присланный ему текст. Пример взаимодействия с подобным ботом см. ниже:

> **Person:** `@responder-bot` test
>
> **Bot:** Вы сказали: @responder-bot test
>
> **Person:** `@responder-bot` привет!
>
> **Bot:** Вы сказали: @responder-bot привет!

##### Требования к реализации

В качестве решения хотелось бы получить ссылку на git репозиторий в котором находятся
исходники Spring Boot приложения выполняющего логику бота.
Все параметры необходимые для корректного запуска и проверки должны задаваться в
конфигурационных файлах (необходимо решить какие именно параметры).
Все сущности с помощью которых осуществляется взаимодействие должны быть представлены
в виде POJO.
В readme должен быть описан процесс запуска приложения и необходимые параметры конфигурации.
Качество кода и выбранная внутренняя структура компонентов/сервисов также оценивается.

**Важно! Нельзя использовать готовые библиотеки-реализации api для Java.**

При реализации может потребоваться использование внешних https адресов для локальной машины.
Для этого можно использовать [ngrok](https://ngrok.com/).
