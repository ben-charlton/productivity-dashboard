import React, { useState } from 'react';

function GoalForm({ createGoal }) {
  const [title, setTitle] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [desiredHourTarget, setDesiredHourTarget] = useState(0);

  const handleSubmit = (e) => {
    e.preventDefault();
    const goal = { title, startDate, endDate, desiredHourTarget, currentHourProgress: 0 };
    createGoal(goal);
    setTitle('');
    setStartDate('');
    setEndDate('');
    setDesiredHourTarget(0);
  };

  return (
    <div id="goal-form">
      <h2>Create Goal</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Enter goal title"
        />
        <input
          type="date"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />
        <input
          type="date"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
        />
        <input
          type="number"
          value={desiredHourTarget}
          onChange={(e) => setDesiredHourTarget(parseInt(e.target.value))}
          placeholder="Desired hour target"
        />
        <button type="submit">Create</button>
      </form>
    </div>
  );
}

export default GoalForm;
