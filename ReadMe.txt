Создание исполняемого файла в Java

Описание: десктопная программа, которая запускается обычным .exe файлом и позволяет останавливать и запускать различные процессы и службы.

Стек технологий: IntelliJIdea; java 1.8; javaFX; команды для PowerShell

Инструкция по использованию:
1) клонируем или скачиваем проект с репозитория
2) открываем проект в IntelliJIdea
3) указываем настройки для сборки javaFX дистрибутива: File -> Project Structure -> Project Settings -> Artifact -> Нажимаем "+" -> JavaFX Application -> From module command (смотри настройки на скриншоте settings.png)
4) билдим проект: в строке меню нажимаем Build -> Build Artifacts -> Build
5) появилась директория out. по директории ".../out/artifacts/command/bundles/command/command.exe" находится исполняемый файл, котоый можно запускать проект.
6) чтобы проект запускался на любых компьютерах без установки JRE и прочх библиотек, необходимо полность копировать всю папку "command" из директории: ".../out/artifacts/command/bundles/..."

По всем вопросам можно обращаться в почту kolya-zayko@rambler.ru или vk https://vk.com/nick_zayko
