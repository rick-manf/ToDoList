import { useState, useCallback } from 'react';
import UserSelect from '../components/UserSelect';
import UserForm from '../components/UserForm';
import TaskList from '../components/TaskList';
import TaskForm from '../components/TaskForm';

export default function Home() {
  const [selectedUser, setSelectedUser] = useState(null);
  const [showUserForm, setShowUserForm] = useState(false);
  const [userRefresh, setUserRefresh] = useState(0);
  const [taskRefresh, setTaskRefresh] = useState(0);

  const refreshTasks = useCallback(() => setTaskRefresh((k) => k + 1), []);
  const refreshUsers = useCallback(() => setUserRefresh((k) => k + 1), []);

  return (
    <div className="mx-auto max-w-2xl px-4 py-8">
      <h1 className="mb-6 text-3xl font-bold text-gray-900">To-Do List</h1>

      {/* User selection */}
      <section className="mb-6 space-y-3">
        <div className="flex items-center gap-2">
          <div className="flex-1">
            <UserSelect
              selectedUser={selectedUser}
              onSelect={setSelectedUser}
              refreshKey={userRefresh}
            />
          </div>
          <button
            onClick={() => setShowUserForm(!showUserForm)}
            className="rounded-lg bg-gray-800 px-4 py-2 text-white hover:bg-gray-900"
          >
            {showUserForm ? 'Chiudi' : 'Nuovo Utente'}
          </button>
        </div>

        {showUserForm && (
          <UserForm
            onCreated={() => {
              refreshUsers();
              setShowUserForm(false);
            }}
            onCancel={() => setShowUserForm(false)}
          />
        )}
      </section>

      {/* Tasks */}
      {selectedUser && (
        <section className="space-y-4">
          <h2 className="text-xl font-semibold text-gray-700">
            Task di {selectedUser.username}
          </h2>

          <TaskForm userId={selectedUser.id} onCreated={refreshTasks} />

          <TaskList
            userId={selectedUser.id}
            refreshKey={taskRefresh}
            onRefresh={refreshTasks}
          />
        </section>
      )}
    </div>
  );
}
