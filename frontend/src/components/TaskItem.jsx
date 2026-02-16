import { updateTask, deleteTask } from '../api/api';

export default function TaskItem({ task, onUpdated }) {
  const toggleCompleted = async () => {
    await updateTask(task.id, { ...task, completed: !task.completed });
    onUpdated();
  };

  const handleDelete = async () => {
    await deleteTask(task.id);
    onUpdated();
  };

  const isOverdue = task.dueDate && !task.completed && new Date(task.dueDate) < new Date();

  return (
    <div
      className={`flex items-center gap-3 rounded-lg border p-3 ${
        task.completed ? 'border-green-200 bg-green-50' : 'border-gray-200 bg-white'
      }`}
    >
      <input
        type="checkbox"
        checked={task.completed}
        onChange={toggleCompleted}
        className="h-5 w-5 rounded accent-indigo-600"
      />
      <div className="flex-1 min-w-0">
        <p className={`font-medium ${task.completed ? 'text-gray-400 line-through' : 'text-gray-800'}`}>
          {task.title}
        </p>
        {task.description && (
          <p className="text-sm text-gray-500 truncate">{task.description}</p>
        )}
        {task.dueDate && (
          <p className={`text-xs ${isOverdue ? 'text-red-500 font-semibold' : 'text-gray-400'}`}>
            Scadenza: {task.dueDate}
          </p>
        )}
      </div>
      <button
        onClick={handleDelete}
        className="rounded px-2 py-1 text-sm text-red-500 hover:bg-red-50 hover:text-red-700"
      >
        Elimina
      </button>
    </div>
  );
}
