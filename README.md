# 🎮 WordGame - Kelime Yakalama Mobil Oyunu

Mobil cihazlar için geliştirilen dinamik ve etkileşimli bir kelime oyunu uygulaması.  
Oyuncular grid üzerinde komşu harfleri birleştirerek anlamlı kelimeler oluşturur, puan kazanır ve skor tablosunda ilerlemelerini takip eder.

---

## ✨ Özellikler

- 📱 Android tabanlı mobil oyun
- 🔤 Türkçe kelime doğrulama sistemi
- 🎯 Farklı zorluk seviyeleri
- 🧠 Dinamik grid yapısı
- ⚡ Gerçek zamanlı kelime kontrolü
- 🪂 Yerçekimi algoritması
- 💾 SQLite tabanlı skor kayıt sistemi
- 📊 Skor tablosu
- 🎨 Material Design uyumlu arayüz
- 🧵 Thread kullanımı ile akıcı performans

---

## 🛠️ Kullanılan Teknolojiler

- **Java**
- **Android Studio**
- **SQLite**
- **XML**
- **RecyclerView**
- **Canvas & Custom View**
- **Thread / Async işlemler**
- **Object Oriented Programming (OOP)**

---

## 🎯 Oyun Mekaniği

### Grid Yapısı

| Seviye | Grid Boyutu |
|---|---|
| Kolay | 10x10 |
| Orta | 8x8 |
| Zor | 6x6 |

---

### Kelime Oluşturma

Oyuncu:

- Yatay
- Dikey
- Çapraz

olacak şekilde komşu harfleri sürükleyerek en az 3 harfli anlamlı kelimeler oluşturabilir.

---

### Yerçekimi Sistemi

Kelime silindikten sonra:

1. Boş hücreler temizlenir
2. Üstteki harfler aşağı kayar
3. Yeni harfler eklenir

---

## 🧱 Proje Mimarisi

```text
java/com.example.wordgame
│
├── activities
├── adapter
├── database
├── engine
├── model
└── ui
```

### Ana Bileşenler

| Sınıf | Görev |
|---|---|
| `GameEngine` | Oyun mantığı ve puan sistemi |
| `GridManager` | Harf üretimi ve yerçekimi |
| `WordListManager` | Kelime doğrulama |
| `DatabaseHelper` | SQLite işlemleri |
| `GameGridView` | Grid çizimi ve kullanıcı etkileşimi |

---

## 📸 Uygulama Ekranları

- Ana Menü
- Kullanıcı Adı Ekranı
- Seviye Seçimi
- Oyun Ekranı
- Skor Tablosu

---

## ⚙️ Kurulum

### Gereksinimler

- Android Studio
- JDK 17+
- Android SDK

### Projeyi Çalıştırma

```bash
git clone https://github.com/kullaniciadi/word-game.git
```

Android Studio ile açtıktan sonra:

```bash
Run > Run App
```

---

## 📈 Performans

- Asenkron thread yapısı sayesinde akıcı oyun deneyimi
- Düşük donanımlı cihazlarda optimize çalışma
- SQLite ile hızlı veri kaydı
- Dinamik grid güncelleme sistemi

