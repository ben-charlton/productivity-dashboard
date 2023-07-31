import React, { useState, useEffect } from 'react';
import firebase from 'firebase/app';
import 'firebase/auth';
import UserProfile from '../../UserProfile';
import GoalTracking from '../../GoalTracking';
import LoginPage from '../../LoginPage';
import firebaseConfig from './firebaseConfig';

// Initialize Firebase
firebase.initializeApp(firebaseConfig);

function App() {
  const [user, setUser] = useState(null);
  const [goals, setGoals] = useState([]);
  const [goalStatistics, setGoalStatistics] = useState(null);

  useEffect(() => {
    const unsubscribe = firebase.auth().onAuthStateChanged((user) => {
      setUser(user);
      if (user) {
        fetchGoals();
        fetchGoalStatistics();
      }
    });

    return () => {
      unsubscribe();
    };
  }, []);

  const fetchGoals = (accessToken) => {
    // Fetch goals from the backend API endpoint (adjust the URL and headers accordingly)
    axios.get('http://localhost:8080/goals', {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
      withCredentials: true, // Include credentials for secure transmission (if applicable)
      httpsAgent: new https.Agent({ rejectUnauthorized: false }), // Adjust HTTPS agent configuration (if needed)
    })
      .then((response) => {
        setGoals(response.data.filter((goal) => goal.userId === user.userId));
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  const fetchGoalStatistics = (accessToken) => {
    // Fetch goal statistics from the backend API endpoint (adjust the URL and headers accordingly)
    axios.get('http://localhost:8080/goals/user/statistics', {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
      withCredentials: true, // Include credentials for secure transmission (if applicable)
      httpsAgent: new https.Agent({ rejectUnauthorized: false }), // Adjust HTTPS agent configuration (if needed)
    })
      .then((response) => {
        setGoalStatistics(response.data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  const createGoal = (goal) => {

    const goalWithUserId = { ...goal, userId: user.userId };

    // Send the goal data to the backend API endpoint (adjust the URL and headers accordingly)
    axios.post('http://localhost:8080/goals', goalWithUserId, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
      withCredentials: true, // Include credentials for secure transmission (if applicable)
      httpsAgent: new https.Agent({ rejectUnauthorized: false }), // Adjust HTTPS agent configuration (if needed)
    })
      .then((response) => {
        if (response.data.userId === user.userId) {
            setGoals((prevGoals) => [...prevGoals, response.data]);
          }
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  const updateGoalProgress = (goalId, hours) => {
    // Send the hours worked to the backend API endpoint (adjust the URL and headers accordingly)
    axios.put(`http://localhost:8080/goals/${goalId}?hours=${hours}`, null, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
      withCredentials: true, // Include credentials for secure transmission (if applicable)
      httpsAgent: new https.Agent({ rejectUnauthorized: false }), // Adjust HTTPS agent configuration (if needed)
    })
      .then(() => {
        fetchGoals(user.accessToken); // Refresh the goals after updating the progress
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  const handleSignOut = () => {
    firebase.auth().signOut();
  };

  if (!user) {
    return <LoginPage />;
  }

  return (
    <div className="App">
      <div className="container">
        <button onClick={handleSignOut}>Sign Out</button>
        <UserProfile goalStatistics={goalStatistics} />
        <GoalTracking
          goals={goals}
          createGoal={createGoal}
          updateGoalProgress={updateGoalProgress}
        />
      </div>
    </div>
  );
}

export default App;
