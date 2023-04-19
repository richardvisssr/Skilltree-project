export const updateSkillTree = (name, description) => {
  console.log("Name is:", name);
  console.log("Description is:", description);

  return {
    type: 'UPDATE_SKILL_TREE',
    payload: { name, description }
  };
};

