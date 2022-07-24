## Gerekli İşlemler ve Değişiklikler

Kayıt olan kullanıcıların 2 tür rolü olabilir: "Admin" ve "User".

Admin rolüne sahip kullanıcılar film ve kategori ekleyebilir, güncelleyebilir ve silebilirler.

User rolüne sahip olan kullanıcılar sistemdeki filmlerden istediklerini beğenip profiline kaydedebilir ve bu işlem üzerinden kullanıcılara film önerisi yapılır.

Kullanıcı tüm endpointlere ulaşabilmek için register olduktan sonra basic auth ile giriş yapmalıdır. Kullanıcı giriş yaptıktan sonra cookie kısmında kullanıcı bilgileri tutulur ve sisteme erişim sağlayabilir.

Sistemin düzgün çalışabilmesi için postman collectiondaki "Do This At Beginning" klasöründeki endpointleri tek tek çalıştırmanız gerekmektedir.

Admin yetkisine sahip kullanıcılar sisteme her film eklediğinde sistemdeki tüm kullanıcılara mail gönderilir.

Kullanıcı userType'ı güncellendiğinde, yani kullanıcı premium olmak istediğinde payment servisi senkron olarak çalışır ve payment modeli mongoDB veritabanına kaydedilir.

Kullanıcı premium değilse maksimum 3 film beğenebilir ve yorum yapamaz.

Json dökümantasyonunu bu linkten import edebilirsiniz.
https://www.postman.com/collections/e48d3ecbadbf85b11209


## SQL Injection nedir ? Nasıl korunabiliriz ?

SQL Injection en yaygın ve temel siber saldırı türlerinden biridir. Bunun yanında bir uygulamanın karşılaşabileceği en yıkıcı tehditlerden biridir. Sql injection saldırıları düzenli olarak veri kayıplarına yol açabilir, veriler çalınabilir, kopyalanabilir. Yani kısacası veritabanındaki özel veriler istendiği şekilde manipüle edilebilir.

Bir web sitesinde herhangi bir form doldururken, arka planda veri tabanına bir SQL sorgusu iletilir. Burada veri tabanı ile bir iletişim sağlanır. Gerekli güvenlik önlemleri alınmamış ise aynı iletişim kanalından farklı bir SQL sorgusu ile veri tabanı manipülasyona açık hale gelmiş olur. SQL injection açığı denilen bu durumda kolaylıkla veriler başka yere transfer edilebilir.

Örnek olarak uygulamamızda bir user login işlemi olurken şöyle bir sorgu gönderildiğini düşünelim: 

`` SELECT * FROM users WHERE username='ozzieonur' AND password= '123456' ``

Saldırıda bulunmak isteyen bir kişi, bu sorgudaki password sorgusunu kaldırarak giriş yapmayı deneyebilir. Bu şekilde veritabanına sızmaya çalışır. Login olma işlemi sırasında atılan POST isteğini adminastor olacak şekilde düzenleyip gönderirse veri tabanına yönetici rolüyle erişebilir:

`` SELECT * FROM users WHERE usarname= 'adminastor'--' AND password= ``

Bu saldırılardan korunmak için çeşitli önlemler almamız gerekmektedir:

``- Parametreli SQL kodu :`` Parametreli sorgular, geliştiricilerin önce tüm SQL kodunu tanımlamasını ve her parametreyi daha sonra sorguya geçirmesini gerektirir. Bu kodlama stili, veri tabanının kod ve veri arasında ayrım yapmasını sağlar. Böylece saldırganların komut ekleseler bile sorgunun amacını değiştirmesini engeller.

``- Örüntü kontrolü :`` Integer, float, boolean, string parametreleri, değerlerinin belirtilen tür için geçerli olup olmadığı kontrol edilebilir. Stringlerin başka paternlara göre de kontrol edilmesi gerekebilir. (tarih, UUID, sadece alfanumerik, vb.)

`` - Kod seviyesinde zorlamak :`` Object-relational mapping (ORM) kütüphanelerini kullanmak, SQL kod yazma gereksinimini ortadan kaldırır. Etkin olan ORM kitaplığı, object-oriented koddan parametrik SQL ifadeleri üretir. Böylece direk sql koduna erişim sağlanamaz.

``- Düzenli güncelleme ve yama :`` Web uygulamalarındaki ve veri tabanlarındaki güvenlik açıkları saldırganların SQL enjeksiyonlarını kullanarak yararlanabileceği bir şeydir. Bu yüzden uygulamalar ve veri tabanları için yamalar ve güncellemeler yayınlandıkça, kullanıcılar mümkün olan en kısa sürede güncellemeleri yapmalıdır.


## Spring Profile Nedir ? 

Spring Boot Profile uygulamamızdaki farklı çalışma isterlerine göre programımızın hangi işlevinin çalışacağını isteğimize göre seçmemizi sağlar. Uygulamaların canlıya alınma aşamalarında dahil olmak üzere farklı ortamlardaki database bağlantısı, servis adresleri, port adresi, security ve cache mekanizmaları gibi konfigrasyonları yönetilebilir hale getirir.
