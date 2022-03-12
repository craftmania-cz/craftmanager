<p align="center">
      <h1 align="center">CraftManager</h1>
</p>
<p align="center">
  <a href="https://java.com/">
    <img src="http://ForTheBadge.com/images/badges/made-with-java.svg" alt="Made with Java">

  </a>
</p>
<p align="center">
    Plugin sloužící k spravování serverů, připojení, VIP, CoinShopu a jiných základních věcí.
</p>

## PvP Arény
### Vytváření
Každá PvP Aréna má svůj svět, pomocí příkazu `//pvp create <id> <name> <dropsEnabled> <effectsEnabled>` vytvoříš PvP Arénu
(musíš se nacházet ve světě, ve kterém chceš vytvořit PvP Arénu).  Pokud už nějaké PvP Aréna s `id` existuje, tak tě to 
varuje a nevytvoří PvP Arénu.
**Poznámka** Je předpokládáno, že v `name` bude znak z Resource Packu

Spawn lokace pro PvP Arénu vytvoříš pomocí `//pvp edit spawn-location add` (je tam více sub commandů jako `list` a `remove`)

**Důležité!** Musíš vytvořit také respawn lokaci, kam se hráči budou teleportovat, když zemřou - `//pvp respawn-location`

### Práva
- Admin příkaz `//pvp` potřebuje `craftmania.at` právo

### Seznam všech příkazů
- `/pvp`, `/pvp-arena`: Pošle to klikací zprávu s seznamem PvP Arén (klik = teleport)
  - `<id>`: Teleportuje hráče do PvP Arény podle ID
- `//pvp`, `//pvp-arena`, `/pvpadmin`: Příkaz pro adminy (vytváření a úprava PvP Arény)
  - `respawn-location`: Nastaví respawn lokaci na momentální lokaci hráče
  - `create <id> <name> <dropsEnabled> <effectsEnabled>`: Vytvoří PvP Arénu
  - `edit`: Sub command pro úpravu PvP Arény
    - `spawn-location`: Sub command pro úpravu Spawn lokací
      - `list`: Seznam Spawn lokací v PvP Aréně s indexy
      - `add`: Přidá Spawn lokaci do PvP Arény na momentální lokaci hráče
      - `remove <index>`: Smaže Spawn lokaci z PvP Arény podle indexu
    - `set`: Sub command pro upravování hodnot PvP Arény
      - `id <id>`: Nastaví ID
      - `name <name>`: Nastaví jméno
      - `drops <true|false>`: Nastaví dropy v PvP Aréně (true = věci dropují)
      - `effects <true|false>`: Nastaví efekty v PvP Aréně (true = efekty jsou povolené)

### Seznam všech eventů
- `PlayerDeathInArenaEvent`: Když hráč zemře v PvP Aréně
- `PlayerKilledPlayerEvent`: Když hráč zabije nějakého hráče v PvP Aréně
- `PlayerTeleportedToArenaEvent`: Když se hráč teleportuje do PvP Arény

### Config
Pro zapnutí PvP Arén (příkazů, listenerů a manageru) je třeba zapnout v configu `pvp.yml` hodnotu `enabled` na `true`.
Data se pak ukládají sama.

`spawn-location-distance`: Jak daleko se zjišťuje, jestli není hráč okolo Spawn lokace v PvP Aréně (defaultně `15`) (v blocích)