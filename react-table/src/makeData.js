import namor from "namor";

const range = len => {
  const arr = [];
  for (let i = 0; i < len; i++) {
    arr.push(i);
  }
  return arr;
};

const newPerson = () => {
  const chanceNumber = Math.random();
  return {
    source: 
      chanceNumber > 0.75 
        ? "cnn"
        : chanceNumber > 0.5 
        ? "Fox"
        : "Breitbart",
    link: 
      chanceNumber > 0.75 
        ? "http://www.cnn.com/"
        : chanceNumber > 0.5 
        ? "http://www.foxnews.com/"
        : "http://www.breitbart.com/",
    linkname: 
      chanceNumber > 0.75 
        ? "cnn title 1"
        : chanceNumber > 0.5 
        ? "fox title 2"
        : "breitbart title 3",
    topic:
      chanceNumber > 0.66
        ? "topic1"
        : chanceNumber > 0.33
        ? "Caucuas"
        : "Election 2020" 
  };
};

export default function makeData(...lens) {
  const makeDataLevel = (depth = 0) => {
    const len = lens[depth];
    return range(len).map(d => {
      return {
        ...newPerson(),
        subRows: lens[depth + 1] ? makeDataLevel(depth + 1) : undefined
      };
    });
  };

  return makeDataLevel();
}
