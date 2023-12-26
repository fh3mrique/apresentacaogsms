#!/bin/bash

# Diretório raiz do monorepo
MONOREPO_DIR="$(pwd)"

# Diretório do backend
BACKEND_DIR="$MONOREPO_DIR/backend"

# Diretório do frontend
FRONTEND_DIR="$MONOREPO_DIR/frontend"

# Iniciar o frontend em segundo plano
echo "Iniciando o frontend..."
cd $FRONTEND_DIR
npm install 
npm run dev &

# Aguardar até que o frontend esteja pronto
echo "Aguardando o frontend estar pronto..."
# Adicione aqui lógica para verificar a prontidão do frontend, por exemplo, verificar se a porta está em uso

# Iniciar o backend (Spring Boot) em segundo plano
echo "Iniciando o backend..."
cd $BACKEND_DIR
./gradlew bootRun &

# Aguardar até que o backend esteja pronto (sem limite de tempo)
echo "Aguardando o backend estar pronto..."
while true; do
    if curl --output /dev/null --silent --head --fail http://localhost:8081; then
        echo "Backend pronto!"
        break
    fi

    sleep 1
done

# Executar os testes do backend
echo "Executando testes no backend..."
./gradlew test

# Você pode adicionar aqui mais testes ou comandos que deseja executar após o backend estar pronto

# Aguardar finalização do frontend
wait

echo "Script concluído!"
