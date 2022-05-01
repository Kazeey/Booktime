export default class List<T>
{
    private items: Array<T>;

    constructor()
    {
        this.items = [];
    }

    size(): number
    {
        return this.items.length;
    }

    add(value: T): void
    {
        this.items.push(value);
    }

    get(index: number): T
    {
        return this.items[index];
    }

    remove(index: number): void
    {
        this.items.splice(index, 1);
    }

    clear(): void
    {
        this.items = [];
    }

    toArray(): Array<T>
    {
        return this.items;
    }

    toString(): string
    {
        return this.items.toString();
    }

    toJSON(): string
    {
        return JSON.stringify(this.items);
    } 

    forEach(callback: (item: T) => void): void
    {
        this.items.forEach(callback);
    }
    
    fromArray(array: Array<T>): List<T>
    {
        this.items = array;
        return this;
    }
}