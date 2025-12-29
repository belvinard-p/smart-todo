import { useState } from 'react'
import './App.css'

function App() {
  const [todos, setTodos] = useState([
    { id: 1, title: "Todo 1", description: "Desc 1", completed: false },
    { id: 2, title: "Todo 2", description: "Desc 2", completed: true },
    { id: 3, title: "Todo 3", description: "Desc 3", completed: false }
  ])

  return (
    <div>
      <h1>Todo app</h1>

      {todos.map((todo) => {
        return (
          <div key={todo.id}>
            <h3>{todo.title}</h3>
            <p>{todo.description}</p>
            <p>{todo.completed ? "Completed" : "Not Completed"}</p>
          </div>
        )
      })}

      <button onClick={() => {
        setTodos((prevTodos) => [
          ...prevTodos,
          {
            id: prevTodos.length + 1,
            title: `Todo ${prevTodos.length + 1}`,
            description: `Desc ${prevTodos.length + 1}`,
            completed: false
          }
        ])
      }}>
        Add Todo
      </button>
    </div>
  )
}

export default App
