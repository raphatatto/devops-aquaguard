# 1. Imagem oficial (PostgreSQL 15)
FROM postgres:15

# 2. Definição das variáveis de ambiente mínimas
ENV POSTGRES_DB=aquaguarddb \
    POSTGRES_USER=aquaguarduser \
    POSTGRES_PASSWORD=supersecret

# 3. Criar (ou garantir) usuário não-root – na imagem oficial, o usuário padrão é 'postgres'
USER postgres

# 4. Definir diretório de trabalho (data dir padrão do Postgres)
WORKDIR /var/lib/postgresql/data

# 5. Expor a porta padrão do PostgreSQL
EXPOSE 5432
