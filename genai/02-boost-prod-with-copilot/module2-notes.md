This lesson focuses on setting up a virtual Python environment and creating a simple to-do application using GitHub Copilot.

Setting Up the Environment
- Install a virtual environment using Python 3 and activate it.
- Create a requirements file to manage dependencies, including Flask and Flask SQL Alchemy.

Creating the Flask Application
- Develop a basic Flask application with a simple route that returns "Hello World."
- Ensure the main function call is included to run the application successfully.

Next Steps and Challenges
- Install the necessary dependencies and run the application.
- Challenge yourself by implementing unit tests and refactoring the to-do application for better functionality.


```console
# Create a virtual environment
python3 -m venv venv
# Activate the virtual environment
source venv/bin/activate
```

Dependencies in requirements.txt:

```plaintext
flask==2.3.3
flask-sqlalchemy==3.1.1
```

Install dependencies:

```console
pip install -r requirements.txt
```

The app:

```python
from flask import Flask
app = Flask(__name__)
@app.route('/')
def hello():
    return "Hello World"
if __name__ == '__main__':
    app.run(debug=True)
```

Run the application:

```console
python app.py
```




