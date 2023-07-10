const mysql = require('mysql');
require('dotenv').config();
const express = require('express');

class PlayerStatusAPI {
  constructor() {
    this.app = express();
    this.db = mysql.createConnection({
      host: process.env.DB_HOST,
      user: process.env.DB_USER,
      password: process.env.DB_PASSWORD,
      database: process.env.DB_NAME
    });

    this.app.use(express.json());

    this.app.get('/playerstatus', this.handleGetPlayerStatus.bind(this));
  }

  connectToDatabase() {
    this.db.connect((error) => {
      if (error) throw error;
      console.log('Connected to MySQL database.');

      this.checkTableExists();
    });
  }

  checkTableExists() {
    const checkTableSql = "SHOW TABLES LIKE 'player_status'";
    this.db.query(checkTableSql, (error, result) => {
      if (error) throw error;

      if (result.length === 0) {
        this.createTable();
      } else {
        console.log('Table already exists.');
      }
    });
  }

  createTable() {
    const sql = `CREATE TABLE player_status (
      id INT AUTO_INCREMENT PRIMARY KEY,
      player_name VARCHAR(255) NOT NULL,
      wing_status BOOLEAN,
      cape_status BOOLEAN,
      skin_status BOOLEAN,
      hdskin_status BOOLEAN,
      ban_status BOOLEAN,
      premium_status BOOLEAN,
      staff_status BOOLEAN,
      date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    )`;

    this.db.query(sql, (error, result) => {
      if (error) throw error;
      console.log('Table created.');

      this.createPlayer();
    });
  }

  createPlayer() {
    const insertSql = `INSERT INTO player_status (player_name, wing_status, cape_status, skin_status, hdskin_status, ban_status, premium_status, staff_status) 
      VALUES ('ozaii', true, true, true, true, false, true, false)`;

    this.db.query(insertSql, (error, result) => {
      if (error) throw error;
      console.log('Player added.');
    });
  }

  handleGetPlayerStatus(req, res) {
    const playerName = req.query.name;

    const sql = 'SELECT wing_status, cape_status, skin_status, hdskin_status, ban_status, premium_status, staff_status FROM player_status WHERE player_name = ?';
    this.db.query(sql, [playerName], (error, results) => {
      if (error) throw error;

      if (results.length > 0) {
        const playerStatus = results[0];
        res.json({
          player_name: playerName,
          wing_status: playerStatus.wing_status,
          cape_status: playerStatus.cape_status,
          skin_status: playerStatus.skin_status,
          hdskin_status: playerStatus.hdskin_status,
          ban_status: playerStatus.ban_status,
          premium_status: playerStatus.premium_status,
          staff_status: playerStatus.staff_status
        });
      } else {
        res.json({
          player_name: playerName,
          wing_status: null,
          cape_status: null,
          skin_status: null,
          hdskin_status: null,
          ban_status: null,
          premium_status: null,
          staff_status: null
        });
      }
    });
  }

  startServer() {
    this.app.listen(3000, () => {
      console.log('API server is running on port 3000');
    });
  }
}

const playerStatusAPI = new PlayerStatusAPI();
playerStatusAPI.connectToDatabase();
playerStatusAPI.startServer();
