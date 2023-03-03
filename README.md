
```
[aemini@lovelace project]$ ./build.sh
[aemini@lovelace project]$ docker compose up
```

* Java 11 demiştiniz ancak normalde 17 ile geliştirme yapıyorum, downgrade etmek istemedim. Bu sebepten build işlemini de docker'e havale edip build script koydum. Umarım sorun olmaz.
* Normalde elim ilk PostreSQL'e gider. PostgreSQL connection stringden veritabanı yaratmaya izin vermez. Bu sebepten docker-file.yaml dosyasındaki **SPRING_DATASOURCE_URL** parametrelerini düzenleyip çalıştırmak gerekli.
* Biraz geç başladım. Zaman ayırabilirsem bunu MySQL'e port edebilirim. MySQL bilgimi hatırlamam gerekiyor.