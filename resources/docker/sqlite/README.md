
OS: windows

step1
```
docker build . -t my-sqlite:latest
```
step2
```
docker volume create sqlite_data
```
step3
```
docker compose up -d
```
step4
```
http://localhost:3000/

open database from /data/initial-db.sqlite
```

p.s
```
docker run -it -v sqlite_data:/data --rm --name my-sqlite my-sqlite
```
