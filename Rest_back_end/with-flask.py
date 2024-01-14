from flask import Flask, jsonify, request, session
from flask_cors import CORS
import mysql.connector
from werkzeug.security import generate_password_hash, check_password_hash

app = Flask(__name__)
CORS(app)
app.secret_key = 'your_secret_key' 
# Function to connect to the MySQL database
def connect_db():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="123456789",
        database="acc",
        port=3306
    )

# Route to get all accidents
@app.route('/accidents', methods=['GET'])
def get_accidents():
    # Connect to the database
    conn = connect_db()
    cursor = conn.cursor(dictionary=True)

    # Execute SQL query to retrieve data
    cursor.execute("SELECT * FROM ma_table")
    accidents = cursor.fetchall()

    # Close the database connection
    conn.close()

    # Return data in JSON format
    return jsonify({'accidents': accidents})

# Route to add a new accident
@app.route('/accidents', methods=['POST'])
def add_accident():
    # Connect to the database
    conn = connect_db()
    cursor = conn.cursor()

    try:
        # Get accident data from the request
        data = request.get_json()
        link = data['link']
        title = data['title']
        description = data['description']

        # Execute SQL query to add a new accident
        cursor.execute("INSERT INTO ma_table (link, title, description) VALUES (%s, %s, %s)",
                       (link, title, description))
        conn.commit()

        result = {'status': 'Accident added successfully'}
    except Exception as e:
        conn.rollback()
        result = {'error': str(e)}
    finally:
        # Close the database connection
        conn.close()

    # Return the result in JSON format
    return jsonify(result)

# Route to delete an accident
@app.route('/accidents/<int:accident_id>', methods=['DELETE'])
def delete_accident(accident_id):
    # Connect to the database
    conn = connect_db()
    cursor = conn.cursor()

    try:
        # Execute SQL query to delete the specified accident
        cursor.execute("DELETE FROM ma_table WHERE id = %s", (accident_id,))
        conn.commit()
        result = {'status': 'Accident deleted successfully'}
    except Exception as e:
        conn.rollback()
        result = {'error': str(e)}
    finally:
        # Close the database connection
        conn.close()

    # Return the result in JSON format
    return jsonify(result)
    







def create_users_table():
    connection = connect_db()
    cursor = connection.cursor()

    create_table_query = '''
        CREATE TABLE IF NOT EXISTS users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(255) NOT NULL,
            password VARCHAR(255) NOT NULL
        )
    '''

    cursor.execute(create_table_query)
    connection.commit()
    cursor.close()
    connection.close()

create_users_table()  # Call the function to create the 'users' table

# Login route (unchanged)
@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data['username']
    password = data['password']

    conn = connect_db()
    cursor = conn.cursor(dictionary=True)
    cursor.execute("SELECT * FROM users WHERE username = %s", (username,))
    user = cursor.fetchone()
    cursor.close()
    conn.close()

    if user and check_password_hash(user['password'], password):
        session['user_id'] = user['id']
        return jsonify({'message': 'Login successful'}), 200
    else:
        return jsonify({'message': 'Invalid credentials'}), 401

# Registration route
@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    username = data['username']
    password = data['password']

    conn = connect_db()
    cursor = conn.cursor()

    try:
        hashed_password = generate_password_hash(password)
        cursor.execute("INSERT INTO users (username, password) VALUES (%s, %s)", (username, hashed_password))
        conn.commit()

        return jsonify({'message': 'Registration successful'}), 201
    except Exception as e:
        conn.rollback()
        return jsonify({'error': str(e)}), 500
    finally:
        cursor.close()
        conn.close()









if __name__ == '__main__':
    app.run(debug=True)
