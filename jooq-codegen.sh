#!/bin/bash

# Запрос пароля
read -r -sp "Введите пароль базы данных: " dbPassword
echo

# Запустите генератор кода JOOQ
mvn jooq-codegen:generate -Dpostgres.password="$dbPassword"