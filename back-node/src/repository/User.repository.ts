import * as mongoDB from 'mongodb';
import User from '../models/User';

export interface UserRepository extends mongoDB.Collection
{
    findByEmailAndPassword(email: string, password: string): Promise<User>;
    findByEmail(email: string): Promise<User>;
}
