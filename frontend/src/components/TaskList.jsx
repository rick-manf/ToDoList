import { useEffect, useState } from 'react';
import { getTasksByUser } from '../api/api';
import TaskItem from './TaskItem';

export default function TaskList({ userId, refreshKey, onRefresh }) {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!userId) {
      setTasks([]);
      return;
    }
    setLoading(true);
    getTasksByUser(userId)
      .then(setTasks)
      .catch(console.error)
      .finally(() => setLoading(false));
  }, [userId, refreshKey]);

  if (!userId) return null;

  if (loading) {
    return <p className="text-center text-gray-400">Caricamento...</p>;
  }

  if (tasks.length === 0) {
    return <p className="text-center text-gray-400">Nessun task. Creane uno!</p>;
  }

  const pending = tasks.filter((t) => !t.completed);
  const completed = tasks.filter((t) => t.completed);

  return (
    <div className="space-y-2">
      {pending.map((t) => (
        <TaskItem key={t.id} task={t} onUpdated={onRefresh} />
      ))}
      {completed.length > 0 && (
        <>
          <p className="pt-2 text-xs font-semibold uppercase text-gray-400">Completati</p>
          {completed.map((t) => (
            <TaskItem key={t.id} task={t} onUpdated={onRefresh} />
          ))}
        </>
      )}
    </div>
  );
}
