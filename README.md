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
