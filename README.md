# instClone - показательный проект портфолио


### присутствуют 4 основных экранов:
* главная страница с публикациями
* поиск новых пользователей
* список активных чатов
* личный профиль пользователя

### А также фрагменты:
* регистрации
* входа
* комментариев
* чата



## Подробное описание экранов:
* На главной странице представлены посты, которые пользователь получает от сервера. К каждому посту приложены лайки, комментарии, а также с каждой записью можно поделиться
* При нажатии на кнопку комментарии, пользователь переходит на новый фрагмент на котором представлены комментарии к текущему посту.
* При нажатии на кнопку поделиться, из нижней части экраны появляется BottomSheet, выбрав на котором друзей и нажав на кнопку, можно поделиться записью.
* При переходе на экран со списком активных чатов, пользователь может нажить либо на иконку пользователя с которым ведется переписка и тогда появится всплывающее окно с информацией о пользователе, либо перейти во фрагмент с перепиской
* Пользователь может нажать на кнопку редактировать профиль, тогда у него появиться возможность: редактировать личные данные(имя, город), загрузить фото из галереи и добавить его в качестве главной фотографии или добавить личную галерею

## Список технологии, которые применялись в процессе разработки:
* **Retrofit**(обеспечивает работу с сервером)
* **Gson**(при работе с сервером)
* **RxJava**(обеспечивает динамическое погружение контента)
* **Coroutine**(для выполнения асинхронных задач)
* **Room**
  
<img src="https://cvws.icloud-content.com/B/AWNC7pTfWKp_8cdd7Bj-AVjuGDKMAZZz6zWaBmOJSr1MeklE8y2kQoQ6/photo_2021-08-05+11.56.33.jpg?o=AiYqGVsw1Z5IXaW3J1jCCcg7h2EJeNIlGfjUR1gmDbs7&v=1&x=3&a=CAogKe4mKF58n0EmHqw-u74WWnv7jeY1mJR1LFEzmP8yqY4SbxCj-dqysS8Yw_CRs7EvIgEAUgTuGDKMWgSkQoQ6aid4mGxTDmf2_K73GK9us30Dj1UqkSU-cIZCYP6MS1noPhdW6IXFiYZyJ_QsJIyOL1NbEM7YO6CZ8b67H7n0IM4E1roCi0Y5drNCw8DtZztX5A&e=1628168288&fl=&r=6b6671f1-e925-4969-ba0a-62007b8c86a0-1&k=_HmIhGjUewyKmDhpaVnngQ&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=EOdMB03VqqrNJFPf_emLnzgvf30&cd=i" width="100" /><img src="https://cvws.icloud-content.com/B/AXAM7joVxgqYvfpkuxVGypgbxT2DARwNZviBFWeO86eWg8MEXzVH4WE8/photo_2021-08-05+11.56.37.jpeg?o=AtM99ac7mUulh9nBXWcQPmVuWAQsgYHnJufU6GAnW5U3&v=1&x=3&a=CAogAV1Z1qS-7vNaNiS4CralpaaY5KKAAKIYeNPVQShpjbkSbxCz-dqysS8Y0_CRs7EvIgEAUgQbxT2DWgRH4WE8aidOk5V9ZfXAafE1r5PiGxuJRGPT8QuL6NXhb6lezdclAd5tNYjH5MJyJ5OCIXcnBnwNq8K-TEpV7rldun3G8489KNrt9sgP64jfITYG-iprxg&e=1628168288&fl=&r=6b6671f1-e925-4969-ba0a-62007b8c86a0-1&k=MbIUb2p2rkTPswqSe4C21A&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=xgaJefA6wy05_Qmane50YDaNiNs&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/AZmCZq6mlb6L0aKn7_yPR5DQJMfKAZrEXOurlLskWsKNXmWi6ZLYfHmN/photo_2021-08-05+11.56.42.jpeg?o=Atu-BOStQ5TiG7xI8S12a_0FbwKolPknrY5R8v-gnnDq&v=1&x=3&a=CAogN9QURbyWGI0wFeFiBQ5TqdqYRl-zXJiDZ8pbsCT4bOYSbxC8-dqysS8Y3PCRs7EvIgEAUgTQJMfKWgTYfHmNaid136N7c0XsZxuB3FUTiO2BiLOVhiy5SPFBPFnWr8nkxryI0vsT6I1yJ3RNJIyZr9WELY2fdEbknOTF59bTbw-rA4FU_NRvlGZ0r4LK8n7vpQ&e=1628168288&fl=&r=6b6671f1-e925-4969-ba0a-62007b8c86a0-1&k=gZOd_J-gO6PJcOlijWPraw&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=X-eI6UUL8Wk-S12MXTWGEbAMlCM&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/AZmUNlprfaNb_NpgtI18yK4RMFvBAS84-7p2UIiXYnkDHitrXbIc8v_X/photo_2021-08-05+11.56.48.jpeg?o=AqFodpjfQDvRLhyp-RGwtO5lvFbmevCGQyNmQaXpjfd9&v=1&x=3&a=CAogb4Szn0kUrMh09PzWWZnzFRcIvL5uTmi9amaUgkzqMC0SbxDG-dqysS8Y5vCRs7EvIgEAUgQRMFvBWgQc8v_XaidiX5wXMTleyRCdOHBq5HMhqAkYYDhOFHf6F_ebosR_bAiRYoSpq9NyJ_VxUFOr7oHTWfXX_COHSl4cp6FS48lbMSIzi9mihFZUaYnAOOKxjg&e=1628168288&fl=&r=6b6671f1-e925-4969-ba0a-62007b8c86a0-1&k=JfPyU3dFb6WoEKkp1hERBg&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=2iMqJPmE3yAidn-9GcbUKHWbGxQ&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/AWSaf4arzNLWJGFSEkUyRskNUE3ZAY-02O308zMJ-dsbEmOxEbuIJ7gJ/photo_2021-08-05+11.56.53.jpeg?o=AtPdZ7fQN1jFdlyMKd5qfBxEqGPI2Qd5ETGev_BYO-C3&v=1&x=3&a=CAog0ITQ_6-P5NQJCG5z-3oapqXsVbjpjzau808D-nn04UQSbxD5mNuysS8YmZCSs7EvIgEAUgQNUE3ZWgSIJ7gJaieMu-cQwj0W2i8KEBBtGGQMCylS64fx9fopflep5hU9qLFidPyGC8lyJ_ZOQBWNAXtJxRGzP0ajW3giJxbRTV6MZvLpOLAgePULH34zXr7oDg&e=1628168292&fl=&r=bc8b85b0-46ab-4b62-b649-98361f2289ed-1&k=-m-qqjSYkAdq0PbyqDXI2Q&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=XwSbL1OqDIFBQdWQR9C0j2O6WBU&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/AeVUSf5zsAkRY98U5spPGH2_jUSOASu7-aNvMMt0lpozk5eqF9C_SeI6/photo_2021-08-05+11.56.57.jpeg?o=Arx1K9CtB4MGgD6EeeqMNRIdoKDsSkO4cPmmrwhcjxYG&v=1&x=3&a=CAogDXPhWLenK-syHUQ4gDFiGAoYNmFigbhOj6B3WHKkA5sSbxCBmduysS8YoZCSs7EvIgEAUgS_jUSOWgS_SeI6aidR4pi80Pf6U10Qrr_1A3eQnT5l5rUK9EE7IYDxnOHxrdXe1AT0cGxyJ3doPwft8gB-owwCDQpA0ff73sZC_q6QuZi_j-ydmaiE4EgoWWwzNw&e=1628168292&fl=&r=bc8b85b0-46ab-4b62-b649-98361f2289ed-1&k=tG3uAeouQ-SnNDWYMCOAzg&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=LRP4J1Sb7ZaabBFEX8Ny6xAES7k&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/Aed9mnzppYNOmhJnOHtf0WC-I0DTAZnCPJAlyza8g8oF_tfJ_yngBJNq/photo_2021-08-05+11.57.02.jpeg?o=ApbGaIN5fKcRH7SP0gmS1LeN5msk67jAp9gax4mhW5kb&v=1&x=3&a=CAogEsfKV3F8gbzn6edXWCAsV78zi8McnS25AAs6g-jk8SgSbxCImduysS8YqJCSs7EvIgEAUgS-I0DTWgTgBJNqaifosH6_Yq7dKyaxgNeqr8hIiDT_9_SdDRcA4lNG9YpQ2DovRIQPlI5yJ_IbHxMxvUKNu38wETRmkkCx6-pqsAE8t6RfcYCCLVdyOYoDELRAuw&e=1628168292&fl=&r=bc8b85b0-46ab-4b62-b649-98361f2289ed-1&k=BNmq2jK6toFXCOZCdsRdlw&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=_ABNgoRtxQOlPv-yau1GFp8wnkI&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/AcY4-c7x-xMqX7LEERWHMHwzJB1mAbHMcT6V6JwWgvvbbo-YlOgo03WL/photo_2021-08-05+11.57.06.jpeg?o=AmLnDZeDLHYcxMhHFRe4nB8abZCQEIbULe4LuXO96rG2&v=1&x=3&a=CAog5ajwE626dXNrOdS7z7rtmb9F0t22afSc2J8qmYtxNo8SbxCPmduysS8Yr5CSs7EvIgEAUgQzJB1mWgQo03WLaicseT3By91GtkRqI_NbSM2OhEKu_0CuEWxEXg6sNuuWFdI2_fqr5uVyJ41_sz-mhqlMMgtx_w3j8LYAOj7YyR4zDQwmz2FSrX0IIroyDAkw-w&e=1628168292&fl=&r=bc8b85b0-46ab-4b62-b649-98361f2289ed-1&k=XcwoX9rDFPit0cdB69UATg&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=nGF594SMTtusn0iwnJzC2Iwgnm0&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/Ae4cXRSqLi_tom2FrqR8TpZRp3_tAQJsNFcb2jdcnC8ts0MTCOqx4CYt/photo_2021-08-05+11.57.10.jpeg?o=ApIjSxcunGBJyVtpBUQ8o8h2wo3-aD5jkToznAzPhuUP&v=1&x=3&a=CAogLJA70FkvQPBcupdhjN-DwLMwhT1ql9moSsE8497VocYSbxCXmduysS8Yt5CSs7EvIgEAUgRRp3_tWgSx4CYtaid6VJqEQZDclS_WSuYteXjJWaOuaGTEkaKmIp2zc03Zb_J0IDQ-OHRyJ8jfKDJAJ3x9MzHcPXjpZxHkAW35DVdsImI7nX0tmm-Bt2KsqxUsQA&e=1628168292&fl=&r=bc8b85b0-46ab-4b62-b649-98361f2289ed-1&k=5eCez0DtUaNcGZdBL3vw2g&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=-2BsP1SkgGKkQKueb2YTFzFGf4M&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/AV6RVt0MC-iR3myyAzo5AqmEJmrpAWNh1NpL8S8P-UBHCqsr8y9m_VnG/photo_2021-08-05+11.57.14.jpeg?o=AvvhjmgiHaxo1mIzDOk6eNYSVAwQaZZ594Np00WfYQYr&v=1&x=3&a=CAogztJ10_ekArDrS6_eHQu8S7i2RNBmCYnHfiMwSE_bpWQSbxCemduysS8YvpCSs7EvIgEAUgSEJmrpWgRm_VnGaid2k_SC4tcAYrHXcXUOINPr8gJetoMFI8IN0SCVYYCNpaItZef-NFdyJ68qoMvis250v85b-NZ4w_D9cgaPhi7RBB0XPZa0qdUO1aM1sjDDNQ&e=1628168292&fl=&r=bc8b85b0-46ab-4b62-b649-98361f2289ed-1&k=eVaVrsZATDC0AQJvTJF37Q&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=RWfy4tOpeAY-MMXp-j_0Z4lxWXg&cd=i" width="100" />
<img src="https://cvws.icloud-content.com/B/ASncCjt1-keu4H07IFF6Q6RLwItkAdON-7xKJSXjGYnS4ssWv9YH1yL0/photo_2021-08-05+11.57.18.jpeg?o=AlYq_3ld_phPpKpBDJl-mxiWOiLqgDVwEA4W_FHcvfK9&v=1&x=3&a=CAogJrxTKclvnUR6mr8GP6cpfeU3p4P_mw9nLSsL8DQsC0ISbxCr79yysS8Yy-aTs7EvIgEAUgRLwItkWgQH1yL0aifaqnn0tNRDgR1rzmasBOe6OReadjfNkcinQr6Nr2WmcvI6s0YkOFxyJ-ZsOFUW-XKXoZ8ShutyZ27uu_raM3g5R1D9V2N1oETlSqMmgoN_mQ&e=1628168319&fl=&r=ddba45fa-4ca1-484c-aa00-c3912aca6566-1&k=UXtP8aSgHgKZowDGj1qE4A&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=66&s=sH47muboUXkwt5unPsJQPmomPWY&cd=i" width="100" />
