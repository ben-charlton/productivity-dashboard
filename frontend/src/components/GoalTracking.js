import React from 'react';
import GoalForm from './GoalForm';
import GoalList from './GoalList';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';

const localizer = momentLocalizer(moment);

function GoalTracking({ goals, createGoal, updateGoalProgress }) {
  return (
    <div className="calendar-section">
      <h2>Goal Tracking Calendar</h2>
      <GoalForm createGoal={createGoal} />
      <GoalList goals={goals} updateGoalProgress={updateGoalProgress} />
      <Calendar
        localizer={localizer}
        events={goals}
        startAccessor="startDate"
        endAccessor="endDate"
        defaultDate={new Date()}
        views={['month', 'week', 'day']}
      />
    </div>
  );
}

export default GoalTracking;
