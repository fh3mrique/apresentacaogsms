LIBERAR ACESSO NO WINDOWS
chmod +x setup.sh

SCRIPT SECUNDARIO

#!/bin/bash


MONOREPO_DIR="$(pwd)"


BACKEND_DIR="$MONOREPO_DIR/backend"


FRONTEND_DIR="$MONOREPO_DIR/frontend"


echo "Iniciando o frontend..."
cd $FRONTEND_DIR
npm install # ou yarn install
npm run dev &


echo "Aguardando o frontend estar pronto..."



echo "Iniciando o backend..."
cd $BACKEND_DIR
./gradlew bootRun &


echo "Aguardando o backend estar pronto..."
attempts=0
max_attempts=30
while true; do
    ((attempts++))
    if curl --output /dev/null --silent --head --fail http://localhost:8080; then
        echo "Backend pronto!"
        break
    fi

    if [ $attempts -eq $max_attempts ]; then
        echo "Timeout: Não foi possível iniciar o backend dentro do tempo esperado."
        exit 1
    fi

    sleep 1
done


echo "Executando testes no backend..."
./gradlew test


wait

echo "Script concluído!"


