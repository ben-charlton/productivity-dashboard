import React from 'react';
//npm install react-big-calendar moment


// component
import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';

const localizer = momentLocalizer(moment);

function GoalList({ goals, updateGoalProgress }) {
  const handleProgressUpdate = (goalId, hours) => {
    updateGoalProgress(goalId, hours);
  };

  // Prepare events for the calendar
  const events = goals.map((goal) => ({
    id: goal.id,
    title: goal.title,
    start: new Date(goal.startDate),
    end: new Date(goal.endDate),
    progress: goal.currentHourProgress,
    target: goal.desiredHourTarget,
  }));

  // Custom event component to display progress
  const EventComponent = ({ event }) => (
    <div>
      <strong>{event.title}</strong>
      <br />
      Progress: {event.progress} / {event.target} hours
      <br />
      <input
        type="number"
        min="0"
        onChange={(e) => handleProgressUpdate(event.id, parseInt(e.target.value))}
        placeholder="Add hours worked"
      />
    </div>
  );

  return (
    <div id="goal-list">
      <h2>Goals</h2>
      <Calendar
        localizer={localizer}
        events={events}
        startAccessor="start"
        endAccessor="end"
        defaultDate={new Date()}
        views={['month', 'week', 'day']}
        components={{
          event: EventComponent,
        }}
      />
    </div>
  );
}

export default GoalList;

