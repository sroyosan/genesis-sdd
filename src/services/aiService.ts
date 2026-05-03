import { Message } from "../types";

const BACKEND_URL = "http://localhost:8181";

export async function chatWithAI(messages: Message[]) {
  const response = await fetch(`${BACKEND_URL}/api/chat`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ messages }),
  });

  if (!response.ok) {
    throw new Error(`Backend error: ${response.statusText}`);
  }

  const data = await response.json();
  return {
    text: data.textContent,
    toolCalls: data.toolCalls || []
  };
}
