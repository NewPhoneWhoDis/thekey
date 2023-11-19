export interface ItemProps {
  word: string;
  count: number;
}

const Item: React.FC<ItemProps> = ({ word, count }: ItemProps) => {
  return (
    <div>
      <h2>
        Current word is: {word} and its count is {count}
      </h2>
    </div>
  );
};

export default Item;
