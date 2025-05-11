import { useState, useEffect } from "react";

const faqs = [
  {
    q: "Which social platforms are supported?",
    a: "We currently support Instagram, YouTube, Twitter/X, TikTok, and Twitch with more platforms coming soon."
  },
  {
    q: "Can I monitor competitor accounts?",
    a: "Yes! You can track any public account across the supported platforms."
  },
  {
    q: "Does this help me grow my followers?",
    a: "While Folo doesn't directly increase followers, the analytics help you understand what content works best."
  },
  {
    q: "Is there a limit to how many accounts I can track?",
    a: "The free version allows tracking up to 3 accounts. Premium unlocks unlimited tracking."
  }
];

export function FAQSection() {
  const [visibleQuestions, setVisibleQuestions] = useState<Array<number>>([]);
  const [typingIndicators, setTypingIndicators] = useState<Array<number>>([]);
  const [visibleAnswers, setVisibleAnswers] = useState<Array<number>>([]);

  // Auto-reveal questions and answers with sequence animation
  useEffect(() => {
    // First, show all questions with slight delays between them
    const questionTimers: NodeJS.Timeout[] = [];
    faqs.forEach((_, idx) => {
      const timer = setTimeout(() => {
        setVisibleQuestions(prev => [...prev, idx]);

        // After each question appears, show typing indicator then answer
        const typingTimer = setTimeout(() => {
          setTypingIndicators(prev => [...prev, idx]);

          // After typing animation, show answer
          const answerTimer = setTimeout(() => {
            setTypingIndicators(prev => prev.filter(i => i !== idx));
            setVisibleAnswers(prev => [...prev, idx]);
          }, 1500); // Typing duration

          questionTimers.push(answerTimer);
        }, 800); // Delay after question appears

        questionTimers.push(typingTimer);
      }, idx * 1200); // Stagger each question

      questionTimers.push(timer);
    });

    // Clean up all timers on unmount
    return () => {
      questionTimers.forEach(timer => clearTimeout(timer));
    };
  }, []);

  // Animation effect for answer bubbles
  useEffect(() => {
    if (visibleAnswers.length > 0) {
      const elements = document.querySelectorAll('.answer-bubble');
      elements.forEach(el => {
        setTimeout(() => {
          el.classList.add('show');
        }, 50);
      });
    }
  }, [visibleAnswers]);

  return (
    <section className="w-full py-16 md:py-24 bg-black bg-opacity-70 backdrop-blur-sm" aria-labelledby="faq-heading">
      <div className="container mx-auto px-6 md:px-8">
        <div className="flex flex-col items-center justify-center space-y-4 text-center mb-12">
          <h2 id="faq-heading" className="text-2xl font-semibold tracking-tight sm:text-3xl md:text-4xl text-white">
            Frequently Asked Questions
          </h2>
        </div>

        <div className="max-w-3xl mx-auto bg-[#1C1C1E] rounded-3xl p-6 shadow-lg">
          <div className="flex items-center justify-center border-b border-[#3C3C3E] pb-3 mb-4">
            <div className="h-1 w-8 bg-[#3C3C3E] rounded-full"></div>
          </div>

          {faqs.map((item, idx) => (
            <div key={idx} className="mb-8">
              {/* Question (right side, blue bubble) */}
              {visibleQuestions.includes(idx) && (
                <div className="flex justify-end mb-4 question-bubble"
                  style={{
                    opacity: 0,
                    transform: 'translateY(10px)',
                    animation: 'fadeSlideIn 0.5s forwards',
                    animationDelay: `${idx * 0.2}s`
                  }}>
                  <div className="bg-[#0A84FF] text-white rounded-2xl rounded-tr-sm px-4 py-3 max-w-[80%] shadow-sm">
                    <p className="text-sm">{item.q}</p>
                  </div>
                </div>
              )}

              {/* Typing indicator */}
              {typingIndicators.includes(idx) && (
                <div className="flex justify-start mb-2">
                  <div className="bg-[#3C3C3E] rounded-2xl rounded-tl-sm px-4 py-3 shadow-sm" style={{ minWidth: '60px' }}>
                    <div className="typing-indicator">
                      <span></span>
                      <span></span>
                      <span></span>
                    </div>
                  </div>
                </div>
              )}

              {/* Answer (left side, gray bubble) */}
              {visibleAnswers.includes(idx) && (
                <div className="flex justify-start mb-2 answer-container">
                  <div className="answer-bubble bg-[#3C3C3E] text-white rounded-2xl rounded-tl-sm px-4 py-3 max-w-[80%] shadow-sm opacity-0 transform translate-y-2 transition-all duration-300 ease-out">
                    <p className="text-sm">{item.a}</p>
                  </div>
                </div>
              )}
            </div>
          ))}

          <div className="flex items-center justify-center mt-6">
            <div className="h-1 w-16 bg-[#3C3C3E] rounded-full"></div>
          </div>
        </div>
      </div>
    </section>
  );
}