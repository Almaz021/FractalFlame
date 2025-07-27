Учебный проект, выполненый в рамках первого семестра [_Академии Бэкенда_](https://education.tbank.ru/academy/backend/) от Т-Образования

Направлен на изучение многопоточности в Java

## Результат работы
<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/8b05cd4d-70db-4b26-a47e-6a4d17b74443" />
<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/1205d3b3-c5f0-4134-b4f1-b5ce67173cdd" />
<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/d15cbf9d-8667-407c-9936-529aeb178c0d" />
<img width="1280" height="724" alt="image" src="https://github.com/user-attachments/assets/81ff242c-1896-4c41-bd7f-950a6347a8ed" />
<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/5f57db48-6dbb-4ba0-b33c-21be00fe54f2" />

-----------------------

Для того чтобы собрать проект, и проверить, что все работает корректно, можно
запустить из модального окна IDEA
[Run Anything](https://www.jetbrains.com/help/idea/running-anything.html)
команду:

```shell
mvn clean verify
```

Альтернативно можно в терминале из корня проекта выполнить следующие команды.

Для Unix (Linux, macOS, Cygwin, WSL):

```shell
./mvnw clean verify
```

Для Windows:

```shell
mvnw.cmd clean verify
```

Для окончания сборки потребуется подождать какое-то время, пока maven скачает
все необходимые зависимости, скомпилирует проект и прогонит базовый набор
тестов.

Если вы в процессе сборки получили ошибку:

```shell
Rule 0: org.apache.maven.enforcer.rules.version.RequireJavaVersion failed with message:
JDK version must be at least 22
```

Значит, версия вашего JDK ниже 22.

Если же получили ошибку:

```shell
Rule 1: org.apache.maven.enforcer.rules.version.RequireMavenVersion failed with message:
Maven version should, at least, be 3.8.8
```

Значит, у вас используется версия maven ниже 3.8.8. Такого не должно произойти,
если вы запускаете сборку из IDEA или через `mvnw`-скрипты.
