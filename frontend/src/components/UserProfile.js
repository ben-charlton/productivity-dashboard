import React from 'react';

function UserProfile({ goalStatistics }) {
  return (
    <div className="profile-section">
      <h2>User Profile</h2>
      {goalStatistics && (
        <div>
          <p>Total Hours Worked: {goalStatistics.totalHoursWorked}</p>
          <p>Completion Rate: {Math.round(goalStatistics.completionRate * 100)}%</p>
          {/* Add more user profile statistics as needed */}
        </div>
      )}
    </div>
  );
}

export default UserProfile;
