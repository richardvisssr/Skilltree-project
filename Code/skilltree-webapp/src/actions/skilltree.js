export const updateSkillTree = (title, description) => {
  console.log("title is:", title);
  console.log("Description is:", description);

  return {
    type: 'UPDATE_SKILL_TREE',
    payload: { title, description }
  };
};

