import apiURL from "../../utils/constants/index";
import axios from "axios";
import { getBooksList, getBookById } from "./BookService";

jest.mock("axios");

const books = [
    {
        "id": 1,
        "title": "The Lord of the Rings",
        "author": "J.R.R. Tolkien",
    }
];
