const JSON_HEADERS = { 'Content-Type': 'application/json' };

async function request(url, options = {}) {
  const res = await fetch(url, options);
  if (!res.ok) {
    const text = await res.text();
    throw new Error(text || res.statusText);
  }
  if (res.status === 204) return null;
  return res.json();
}

// ---- Users ----

export function getUsers() {
  return request('/users');
}

export function getUser(id) {
  return request(`/users/${id}`);
}

export function createUser(data) {
  return request('/users', {
    method: 'POST',
    headers: JSON_HEADERS,
    body: JSON.stringify(data),
  });
}

export function updateUser(id, data) {
  return request(`/users/${id}`, {
    method: 'PUT',
    headers: JSON_HEADERS,
    body: JSON.stringify(data),
  });
}

export function deleteUser(id) {
  return request(`/users/${id}`, { method: 'DELETE' });
}

// ---- Tasks ----

export function getTasksByUser(userId) {
  return request(`/tasks/user/${userId}`);
}

export function getTask(id) {
  return request(`/tasks/${id}`);
}

export function createTask(data) {
  return request('/tasks', {
    method: 'POST',
    headers: JSON_HEADERS,
    body: JSON.stringify(data),
  });
}

export function updateTask(id, data) {
  return request(`/tasks/${id}`, {
    method: 'PUT',
    headers: JSON_HEADERS,
    body: JSON.stringify(data),
  });
}

export function deleteTask(id) {
  return request(`/tasks/${id}`, { method: 'DELETE' });
}
