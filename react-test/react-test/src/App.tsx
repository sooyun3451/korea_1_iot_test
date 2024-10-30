import axios from "axios";
import React, { useEffect, useState } from "react";

const DOMAIN = "http://localhost:8080";
const BOOK_API = "api/test/books";

interface GetBook {
  id: number;
  bookTitle: string;
  bookAuthor: string;
  bookPublicationYear: number;
  category: Category;
}

type Category = "인문" | "사회" | "과학기술" | "기타";

export default function App() {
  const [category, setCategory] = useState<Category>("기타");

  const [book, setBook] = useState<GetBook>({
    id: 0,
    bookTitle: "",
    bookAuthor: "",
    bookPublicationYear: 0,
    category: "기타",
  });

  const [results, setResults] = useState<GetBook[]>([]);
  const handleGetBooks = async () => {
    try {
      const response = await axios.get(`${DOMAIN}/${BOOK_API}/list`);
      const data = response.data.data;
      setResults(data);
    } catch (error) {
      console.error(error);
    }
  };

  const fetchCategoryData = async (category: string) => {
    if (category.trim()) {
      try {
        const response = await axios.get(
          `${DOMAIN}/${BOOK_API}/search/category`,
          { params: { category } }
        );
        const data = response.data.data;
        setResults(data);
      } catch (error) {
        console.error(error);
      }
    }
  };

  const handleButtonClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    const selectCategory = e.currentTarget.value as Category;
    setCategory(selectCategory);
  };

  useEffect(() => {
    handleGetBooks();
  }, []);

  useEffect(() => {
    fetchCategoryData(category);
  }, [category]);

  return (
    <div style={{ width: "100%" }}>
      <div
        className="search-box"
        style={{ width: "400px", margin: "auto", marginTop: "10px"}}
      >
        <button
          value="인문"
          onClick={handleButtonClick}
          style={{
            width: "70px",
            height: "30px",
            border: "none",
            marginRight: "35px", 
            borderRadius: "7px",
            backgroundColor: 'lightBlue',
            cursor: 'pointer'
          }}
        >
          인문
        </button>
        <button
          value="사회"
          onClick={handleButtonClick}
          style={{
            width: "70px",
            height: "30px",
            border: "none",
            marginRight: "35px", 
            borderRadius: "7px",
            backgroundColor: 'lightBlue',
            cursor: 'pointer'
          }}
        >
          사회
        </button>
        <button
          value="과학기술"
          onClick={handleButtonClick}
          style={{
            width: "70px",
            height: "30px",
            border: "none",
            marginRight: "35px", 
            borderRadius: "7px",
            backgroundColor: 'lightBlue',
            cursor: 'pointer'
          }}
        >
          과학기술
        </button>
        <button
          value="기타"
          onClick={handleButtonClick}
          style={{
            width: "70px",
            height: "30px",
            border: "none",
            borderRadius: "7px",
            backgroundColor: 'lightBlue',
            cursor: 'pointer'
          }}
        >
          기타
        </button>
      </div>
      {results.map((result, index) => (
        <div
          key={index}
          style={{
            width: "400px",
            height: "150px",
            border: "1px solid black",
            borderRadius: "20px",
            margin: "10px auto",
            textAlign: "center",
            alignItems: "center",
          }}
        >
          <p>제목: {result.bookTitle}</p>
          <p>저자: {result.bookAuthor}</p>
          <p>출판년도: {result.bookPublicationYear}</p>
          <p>카테고리: {result.category}</p>
        </div>
      ))}
    </div>
  );
}